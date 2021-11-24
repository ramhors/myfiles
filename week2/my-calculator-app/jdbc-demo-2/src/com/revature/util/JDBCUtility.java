package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import org.postgresql.Driver;

public class JDBCUtility {

	public static Connection getConnection() {
		
		/****************************************************************************
		 * Utility class is a class that contains static members
		 * Collection class from Collection API that contains useful static method 
		 * to work with various collections
		 ***************************************************************************/
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "Tongasoa";
		
		Driver postgresDriver = new Driver();
		DriverManager.registerDriver(postgresDriver);
		
		Connection conn = 
			
	}
}
