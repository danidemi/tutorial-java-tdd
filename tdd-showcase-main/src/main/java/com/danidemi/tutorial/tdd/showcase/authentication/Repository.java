package com.danidemi.tutorial.tdd.showcase.authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Repository {

	public User findUserByUsername(String username) throws SQLException {
		User user = null;
		Connection connection = getConnection();
		String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
		PreparedStatement s = connection.prepareStatement(sql);
		s.setString(1, username);
		ResultSet rs = s.executeQuery();
		if (rs.next()) {
			/*
			 * create a new user with values from DB
			 */
		}
		return user;
	}

	private Connection getConnection() {
		/*
		 * gets and returns a collection
		 */
		return null;
	}
	
	public void logAccess(User user) throws SQLException {
		Connection connection = getConnection();
		String sql = "INSERT INTO LOG (TIMESTAMP, USER) VALUES (?,?)";
		PreparedStatement s = connection.prepareStatement(sql);
		s.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
		s.setString(2, user.getUsername());
		s.executeUpdate();
	}
}
