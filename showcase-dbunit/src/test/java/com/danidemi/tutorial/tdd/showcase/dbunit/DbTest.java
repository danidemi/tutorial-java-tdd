package com.danidemi.tutorial.tdd.showcase.dbunit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.isNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;
import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class DbTest {
	
	static String driverClass = org.hsqldb.jdbcDriver.class.getName();
	static String connectionUrl = "jdbc:hsqldb:mem:aname";
	static String username = "sa";
	static String password = "";
	
	JdbcDatabaseTester dbTester;
	
	@Rule public TestName testName = new TestName();
	
	@BeforeClass
	public static void setUpDatabase() throws Exception {
		Class.forName(org.hsqldb.jdbcDriver.class.getName());
		Connection connection = DriverManager.getConnection(connectionUrl, username, password);
		connection.createStatement().executeUpdate( IOUtils.toString( DbTest.class.getResourceAsStream("CreateDb.sql") ) );		
	}

	@Before
	public void setUp() throws SQLException, IOException, Exception {
		dbTester = new JdbcDatabaseTester( driverClass, connectionUrl, username, password);		
	}

	@Test
	public void insertMovie() throws Exception {
		
		// given
		// ...the initial dataset that should be loaded into the db
		InputStream initialDataSetResource = getClass().getResourceAsStream( getClass().getSimpleName() + "." + testName.getMethodName() + ".xml" );
		XmlDataSet initialDataSet = new XmlDataSet( initialDataSetResource );		
		
		// ...the db initialized with the initial dataset
		IDatabaseConnection conn = dbTester.getConnection();
		DatabaseOperation.CLEAN_INSERT.execute(conn, initialDataSet);
		
		// ...a new movie dao
		MovieDao tested = new MovieDao();
		
		
		
		// when
		// ...a new movie is created
		int rowsBefore = conn.getRowCount("Movie");
		tested.createMovie(dbTester.getConnection().getConnection(), "MyMovie", 1999, "MGM" );
		int rowsAfter = conn.getRowCount("Movie");
		
		
		
		// then
		// ...one row has to be created in movie
		assertThat( rowsAfter - rowsBefore, equalTo(1) );
		
	}
	
	@Test
	public void doNotInsertAMovieWithUnknownPublisher() throws Exception {
		
		// given
		// ...the initial dataset that should be loaded into the db
		InputStream initialDataSetResource = getClass().getResourceAsStream( getClass().getSimpleName() + "." + testName.getMethodName() + ".xml" );
		XmlDataSet initialDataSet = new XmlDataSet( initialDataSetResource );		
		
		// ...the db initialized with the initial dataset
		IDatabaseConnection conn = dbTester.getConnection();
		DatabaseOperation.CLEAN_INSERT.execute(conn, initialDataSet);
		int originalNumberOfMovies = initialDataSet.getTable("Movie").getRowCount();
		
		// ...a new movie dao
		MovieDao tested = new MovieDao();
		
		
		
		// when
		// ...a new movie is created
		try{
			tested.createMovie(dbTester.getConnection().getConnection(), "MyMovie", 1999, "Unknow Movies Production" );			
		}catch(IllegalArgumentException iae){
			
		}catch(Exception e){
			fail();
		}
		int moviesCount = conn.getRowCount("Movie");
		
		
		
		// then
		// ...one row has to be created in movie
		assertThat( moviesCount, equalTo(originalNumberOfMovies) );
		
	}
	
	@Test 
	public void verifyDataSet() throws Exception {
		
		// given
		// ...the initial dataset that should be loaded into the db
		XmlDataSet initialDataSet = loadDataSet("initial");
		
		// ...the expected dataset after the operation 
		XmlDataSet expectedDataSet = loadDataSet("expected");		
		
		// ...the db initialized with the initial dataset
		IDatabaseConnection conn = dbTester.getConnection();
		DatabaseOperation.CLEAN_INSERT.execute(conn, initialDataSet);	
		
		// ...a new movie dao
		MovieDao tested = new MovieDao();		
		
		// when
		// ...a new movie is created
		tested.createMovie(dbTester.getConnection().getConnection(), "My Movie", 2014, "MGM" );			

		// then
		ITable actualMovieTable = conn.createQueryTable("ExpTable", "Select TITLE, YEAR, PUBLISHER_ID from Movie ORDER BY TITLE");
		ITable expectedMovieTable = expectedDataSet.getTable("Movie");
		Assertion.assertEquals(expectedMovieTable, actualMovieTable);
	}

	private XmlDataSet loadDataSet(String flavour) throws DataSetException {
		InputStream initialDataSetResource = getClass().getResourceAsStream( getClass().getSimpleName() + "." + testName.getMethodName() + "."
				+ flavour
				+ ".xml" );
		XmlDataSet initialDataSet = new XmlDataSet( initialDataSetResource );
		return initialDataSet;
	}

}
