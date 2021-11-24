package com.revature.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Application {

	public static void main(String[] args) {
		
		/*************************************
		 * Define the credentials to connect
		 * to the database
		 * Providing the connection string
		 *************************************/
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "Tongasoa";
		
		/*******************************************************
		 * Registering to the driver
		 * This driver class is from postgres driver dependency
		 *******************************************************/
		Driver postgresDriver = new Driver();
		
		try {
		DriverManager.registerDriver(postgresDriver);
		Connection conn = DriverManager.getConnection(url,username,password);
		System.out.println(conn);
		
		
		/*******************************************
		 * Iterate over the ResultSet to the results
		 * from the database query
		
		}catch(SQLException e) {
			/***************************************************************
			 * SQLExcpetion is checked exception that is part of JDBC. Because it is checked,
			 * and are required to handle or declare this exception
			 */
			e.printStackTrace();
		}
	}

}

