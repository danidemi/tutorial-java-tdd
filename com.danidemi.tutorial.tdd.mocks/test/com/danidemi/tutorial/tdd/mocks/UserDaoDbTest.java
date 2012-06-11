package com.danidemi.tutorial.tdd.mocks;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.InputStream;
import java.sql.DriverManager;

import org.dbunit.JdbcBasedDBTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserDaoDbTest {

	@BeforeClass
	public static void handleSetUpOperation() throws Exception {

		Class.forName("org.hsqldb.jdbcDriver");

		final IDatabaseConnection conn = new DatabaseConnection(
				DriverManager.getConnection(
						"jdbc:hsqldb:hsql://localhost/tdddb", "sa", ""));

		String resourcePath = UserDaoDbTest.class.getSimpleName() + ".xml";
		InputStream resourceAsStream = UserDaoDbTest.class
				.getResourceAsStream(resourcePath);
		if (resourceAsStream == null) {
			throw new NullPointerException(resourcePath + " not found");
		}
		final IDataSet data = new XmlDataSet(resourceAsStream);

		try {
			DatabaseOperation.CLEAN_INSERT.execute(conn, data);
		} finally {
			conn.close();
		}
	}

	@Test
	public void shouldFindAUserByUsername() {

		UserDao userDao = new UserDaoImpl();
		User john = userDao.findUserByUsername("John");
		assertNotNull(john);
		
	}
	
	@Test
	public void shouldReturnNullIfTheUserIsNotFound() {

		UserDao userDao = new UserDaoImpl();
		User user = userDao.findUserByUsername("NOT_THERE");
		assertNull(user);
		
	}	
	
	@Test
	public void shouldCreateANewUser(){
		
		UserDao userDao = new UserDaoImpl();
		User u = userDao.newUser();
		u.setUsername("newuser");
		u.setPassword("pwd");
		userDao.saveOrUpdate(u);
		
		assertNotNull( u.getId() );
	}

}
