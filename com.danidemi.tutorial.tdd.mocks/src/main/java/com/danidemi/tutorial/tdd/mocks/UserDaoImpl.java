package com.danidemi.tutorial.tdd.mocks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl extends AbstractDao implements UserDao {
	
	public UserDaoImpl(JdbcConf jdbcConf) {
		super(jdbcConf);
	}

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
		}.execute(jdbcConf);

	}


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
		}.execute(jdbcConf);
		
	}


	public User newUser() {
		UserImpl userImpl = new UserImpl();
		return userImpl;
	}

}
