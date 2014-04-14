package com.danidemi.tutorial.tdd.mocks;

public class AbstractDao {
	
	protected JdbcConf jdbcConf;

	public AbstractDao(JdbcConf jdbcConf) {
		this.jdbcConf = jdbcConf;
	}
		
}
