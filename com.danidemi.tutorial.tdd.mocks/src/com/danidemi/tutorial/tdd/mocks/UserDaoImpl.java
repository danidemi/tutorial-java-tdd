package com.danidemi.tutorial.tdd.mocks;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
	
	@Override
	public User findUserByUsername(final String username) {

		return new SqlSandwich<User>() {
			
			@Override
			User withConnection(Connection conn) throws Exception {
				PreparedStatement ps = conn.prepareStatement("SELECT username, password, is_locked FROM TDD_USER WHERE username=?");
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					UserImpl u = new UserImpl();
					u.setUsername( rs.getString("username") );
					u.setLocked( rs.getBoolean("is_locked") );
					u.setPassword( rs.getString("password") );
					return u;
				}
				return null;
			}
		}.execute();

	}

	@Override
	public void saveOrUpdate(final User u) {
		
		final UserImpl user = (UserImpl) u;
		
		new SqlSandwich<Void>() {
			
			@Override
			Void withConnection(Connection conn) throws Exception {
				PreparedStatement ps;
				if(user.getId()!=null){
					 ps = conn.prepareStatement("UPDATE TDD_USER SET username=?, password=?,is_locked=? WHERE id=?");
					 ps.setString(1, user.getUsername());
					 ps.setString(2, user.getPassword());
					 ps.setBoolean(3, user.isLocked());
					 ps.setLong(4, user.getId());
					 ps.executeUpdate();
				}else{
					 ps = conn.prepareStatement("INSERT INTO TDD_USER (username,password,is_locked) VALUES(?, ?, ?)", java.sql.Statement.RETURN_GENERATED_KEYS);
					 ps.setString(1, user.getUsername());
					 ps.setString(2, user.getPassword());
					 ps.setBoolean(3, user.isLocked());
					 ps.executeUpdate();
					 ResultSet generatedKeys = ps.getGeneratedKeys();
					 generatedKeys.next();
					 user.setId( generatedKeys.getLong(1) );
				}
				return null;
			}
		}.execute();
		
	}

	@Override
	public User newUser() {
		UserImpl userImpl = new UserImpl();
		return userImpl;
	}

}

abstract class SqlSandwich <T> {
	
	public T execute(){
		Connection connection = null;
        try {
        	Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/tdddb", "sa", "");
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
