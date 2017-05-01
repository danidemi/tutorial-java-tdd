package com.danidemi.tutorial.tdd.showcase.authentication;

import java.sql.SQLException;

public class DatabaseException extends RuntimeException {

	public DatabaseException(SQLException e) {
		super(e);
	}

	/** */
	private static final long serialVersionUID = 2402158290691206310L;

}
