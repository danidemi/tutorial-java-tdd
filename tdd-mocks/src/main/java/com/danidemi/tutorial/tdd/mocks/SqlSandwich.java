package com.danidemi.tutorial.tdd.mocks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class SqlSandwich <T> {
	
	public T execute(JdbcConf jdbcConf){
		Connection connection = null;
        try {
        	Class.forName(jdbcConf.getDriverName());
			connection = DriverManager.getConnection(jdbcConf.getJdbcUrl(), jdbcConf.getUsername(), jdbcConf.getPassword());
			return withConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}			
		}
		return null;
	}
	
	abstract T withConnection(Connection conn) throws Exception;
}