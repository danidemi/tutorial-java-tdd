package com.danidemi.tutorial.tdd.showcase.dbunit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDao {

	public MovieDao() {
	}

	public void createMovie(Connection conn, String title, int year, String publisher) throws SQLException {
		
		PreparedStatement ps = conn.prepareStatement("SELECT Id FROM Publisher WHERE name=?");
		ps.setString(1, publisher);
		ResultSet rs = ps.executeQuery();
		
		Long long1 = null;
		boolean publisherIsKnown = rs.next();
		if(publisherIsKnown){
			long1 = rs.getLong("Id");			
			rs.close();
			ps.close();
		}else{
			rs.close();
			ps.close();
			throw new IllegalArgumentException("Unknown publisher '" + publisher + "'");
		}
		
		
		ps = conn.prepareStatement("INSERT INTO Movie(Title, Year, Publisher_Id) VALUES (?,?,?)");
		ps.setString(1, title);
		ps.setInt(2, year);
		ps.setLong(3, long1);
		
		ps.execute();
		
	}

}
