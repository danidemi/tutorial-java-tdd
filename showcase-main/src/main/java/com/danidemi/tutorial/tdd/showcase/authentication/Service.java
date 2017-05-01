package com.danidemi.tutorial.tdd.showcase.authentication;

import java.sql.SQLException;
import java.util.Date;

public class Service {
	
	Repository repository;
	
	public User login(String username, String password) throws LoginException {
		if (username == null || password == null) {
			throw new IllegalArgumentException();
		}
		User user = null;
		try {
			user = repository.findUserByUsername(username);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		if (user == null) {
			throw new LoginException("wrong username");
		}
		if (!user.getPassword().equals(password)) {
			throw new LoginException("wrong password");
		}
		try {
			repository.logAccess(user);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		return user;
	}
	
	public String generatePassword(long seed) {
		if (Math.abs(seed) == 1L) {
			throw new IllegalArgumentException("abs(seed) must be <> 1");
		}
		return "_"+Long.toHexString((Math.round(Math.pow(seed, 2))))+"!";
	}
	
	public String generatePassword() {
		Date date = new Date();
		return "!"+date.getTime()+"_";
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

}
