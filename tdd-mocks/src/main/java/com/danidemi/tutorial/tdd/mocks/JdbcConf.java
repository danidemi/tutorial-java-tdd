package com.danidemi.tutorial.tdd.mocks;

public class JdbcConf {
	private String driverName;
	private String jdbcUrl;
	private String username;
	private String password;

	public JdbcConf(String driverName, String jdbcUrl, String username,
			String password) {
		this.driverName = driverName;
		this.jdbcUrl = jdbcUrl;
		this.username = username;
		this.password = password;
	}

	public String getDriverName() {
		return driverName;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}