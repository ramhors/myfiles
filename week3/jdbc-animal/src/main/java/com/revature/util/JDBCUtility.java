package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

public class JDBCUtility {
	
	public static Connection getConnection() throws SQLException{
		
		String url = "jdbc:postgresql://locahost:5432/postgres";
		String username = "postgres";
		String password = "Tongasoa";
		
		Driver postgresDriver = new Driver();
		DriverManager.registerDriver(postgresDriver);
		
		Connection conn = DriverManager.getConnection(url, username, password);
		
		return conn;
	}

}
