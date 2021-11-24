package com.revature.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.Driver;

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
		PreparedStatement pst = conn.prepareStatement("SELECT * FROM students");
		ResultSet rs = pst.executeQuery();
		
		/*******************************************
		 * Iterate over the ResultSet to the results
		 * from the database query
		 *******************************************/
		while (rs.next()) {
			int studentId = rs.getInt(1);
			String firstName = rs.getString(2);
			String lastName = rs.getString(3);
			String classification = rs.getString(4);
			int age = rs.getInt(5);
			
			System.out.println("\nStudent Id: " + studentId + ", First Name: " + firstName +
					 ", Last Name: " +  lastName + ", Classification: " + classification + ", Age: " +  age);
		}
		}catch(SQLException e) {
			/***************************************************************
			 * SQLExcpetion is checked exception that is part of JDBC. Because it is checked,
			 * and are required to handle or declare this exception
			 */
			e.printStackTrace();
		}
	}

}
