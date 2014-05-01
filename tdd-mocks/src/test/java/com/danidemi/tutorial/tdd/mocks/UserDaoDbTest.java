package com.danidemi.tutorial.tdd.mocks;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.commons.io.IOUtils;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserDaoDbTest {

	private static JdbcConf jdbcConf;

	@BeforeClass
	public static void handleSetUpOperation() throws Exception {

		String createDatabaseSql = IOUtils.toString(UserDaoDbTest.class.getResource("/database.sql"));
		
		jdbcConf = new JdbcConf("org.hsqldb.jdbcDriver", "jdbc:hsqldb:mem:aname", "sa", "");
		
		Class.forName(jdbcConf.getDriverName());
		Connection connection = DriverManager.getConnection(jdbcConf.getJdbcUrl(), jdbcConf.getUsername(), jdbcConf.getPassword());
		Statement createStatement = connection.createStatement();
		createStatement.addBatch(createDatabaseSql);
		createStatement.executeBatch();

		final IDatabaseConnection conn = new DatabaseConnection(connection);

		String resourcePath = "/" + UserDaoDbTest.class.getSimpleName() + ".xml";
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

		UserDao userDao = new UserDaoImpl(jdbcConf);
		User john = userDao.findUserByUsername("John");
		assertNotNull(john);

	}

	@Test
	public void shouldReturnNullIfTheUserIsNotFound() {

		UserDao userDao = new UserDaoImpl(jdbcConf);
		User user = userDao.findUserByUsername("NOT_THERE");
		assertNull(user);

	}

	@Test
	public void shouldCreateANewUser() {

		UserDao userDao = new UserDaoImpl(jdbcConf);
		User u = userDao.newUser();
		u.setUsername("newuser");
		u.setPassword("pwd");
		userDao.saveOrUpdate(u);

		assertNotNull(u.getId());
	}

}
