package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import org.postgresql.Driver;
import java.sql.SQLException;

public class JDBCUtility {
	
	public static Connection getConnection() throws SQLException {
		
		String url = System.getenv("db_url");
		String username = System.getenv("db_username");
		String password = System.getenv("db_password");
		
		Driver postgreDriver = new Driver();
		DriverManager.registerDriver(postgreDriver);
		
		Connection conn = DriverManager.getConnection(url,username,password);
		
		return conn;
	}

}
