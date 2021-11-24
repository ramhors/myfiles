package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dto.AddOrUpdateStudentDTO;
import com.revature.model.Student;
import com.revature.util.JDBCUtility;

// This StudentDAO class is part of the data access layer
// Recap of the 3 layers of a backend application:
// 	1. Controller layer: contains the controllers that receive the HTTP requests and extract information from the requests.
// 			- The controller layer will interact with the Service layer directly
//  2. Service layer: contains the business logic such as validating proper inputs, processing data, etc.
// 			- The service layer will interact with the data access layer directly
//  3. Data access layer: contains the logic to interact directly with the database
// 			- DAO (data access object): contains the methods to perform CRUD operations
// 			- Utilize JDBC (Java database connectivity API to do so)
public class StudentDAO {
	
	// This class is a blueprint for creating a StudentDAO object
	// So, we will not define static methods, we will instead define instance methods
	// (non-static) methods
	
	// CRUD: Create, Read, Update, Delete
	
	// Generally a good practice for Create methods in a DAO class is to return the object being created
	// The same is true for Updating a particular record
	// SO, we are going to change the return type from void to Student
	public Student addStudent(AddOrUpdateStudentDTO student) throws SQLException {
		
		// try with resources: used when we want for our application to automatically call the .close() method on whatever "resource"
		// we are using
		// The Connection interface defines a close() method. This method, when invoked, will disconnect from the database
		// Whenever we are done with our block of code inside the try-with-resources block, it will automatically do this for us
		// The resource will be closed regardless of whether an exception occurs or the block ends without any exception
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "INSERT INTO students (student_first_name, student_last_name, student_classification, student_age) "
					+ " VALUES (?, ?, ?, ?)";
			
			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, student.getFirstName());
			pstmt.setString(2, student.getLastName());
			pstmt.setString(3, student.getClassification());
			pstmt.setInt(4, student.getAge());
			
			int numberOfRecordsInserted = pstmt.executeUpdate(); // Instead of executeQuery, like we use for SELECT statements, INSERT, UPDATE, and DELETE will use
			// executeUpdate(). This method returns an integer representing the number of rows that were modified
			
			// If numberOfRecordsInserted is NOT 1, then something went wrong
			if (numberOfRecordsInserted != 1) {
				throw new SQLException("Adding a new Student was unsuccessful");
			}
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			rs.next(); // iterating to the first "row"
			int automaticallyGeneratedId = rs.getInt(1); // grabbing the first column's information from that "row"
			
			// When we return the Student that was created in the database,
			// The missing data is the automatically generated ID
			// How do we obtain that id?
			return new Student(automaticallyGeneratedId, student.getFirstName(), student.getLastName(), student.getClassification(), student.getAge());
		}
		
	}
	
	public List<Student> getAllStudents() throws SQLException {
		
		List<Student> listOfStudents = new ArrayList<>();
		
		/*
		 * Steps to query data
		 */
		
		// 1. Obtain a Connection object
		try (Connection con = JDBCUtility.getConnection()) {
			// 2. From the Connection object, obtain a Statement object (Statement, PreparedStatement, CallableStatement)
			String sql = "SELECT * FROM students";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			// 3. Execute the query
			ResultSet rs = pstmt.executeQuery(); // this returns a ResultSet object.
			// A ResultSet is a pointer to the data that was queried that allows us to retrieve row by row of information

			// 4. Iterate over the queried data using the ResultSet object
			// 		rs.next() <- iterate to the next row in the queried data
			// 		rs.next() returns a boolean (true or false)
			// 		it will return true if there is a next row to go to
			// 		it returns false if there is no more rows to go to
			while (rs.next()) { // A while loop keeps going until it becomes false
				
				// 5. Grab all of the information from the current row that we are on
				
				int id = rs.getInt("student_id");
				String firstName = rs.getString("student_first_name");
				String lastName = rs.getString("student_last_name");
				String classification = rs.getString("student_classification");
				int age = rs.getInt("student_age");
				
				// 6. Take that information and create a Student object from that information
				Student s = new Student(id, firstName, lastName, classification, age);
				
				// 7. Add the student object to the list
				listOfStudents.add(s);
			}
		}
		
		return listOfStudents;
	}
	
	public Student getStudentById(int id) throws SQLException {
		
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "SELECT * FROM students WHERE student_id = ?"; // a '?' is placeholder for arguments that you want to pass
			// into a specific query
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, id); // pass the value of the id variable into the 1st question mark (there is only 1 question mark)
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return new Student(rs.getInt("student_id"), rs.getString("student_first_name"), rs.getString("student_last_name"), 
						rs.getString("student_classification"), rs.getInt("student_age"));
			} else {
				return null;
			}
		}
		
	}
	
	// Update student will return a Student object corresponding to the record that was updated, and takes in 2 argument corresponding with the studentId
	// whose row we would like to update, and the AddOrUpdateStudentDTO object containing the properties of what we want to update that row with
	public Student updateStudent(int studentId, AddOrUpdateStudentDTO student) throws SQLException {
		
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "UPDATE students "
					+ "SET student_first_name = ?,"
					+ "		student_last_name = ?,"
					+ "		student_classification = ?,"
					+ "		student_age = ?"
					+ "WHERE "
					+ "student_id = ?;";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, student.getFirstName());
			pstmt.setString(2, student.getLastName());
			pstmt.setString(3, student.getClassification());
			pstmt.setInt(4, student.getAge());
			pstmt.setInt(5, studentId);
			
			int numberOfRecordsUpdated = pstmt.executeUpdate();
			
			if (numberOfRecordsUpdated != 1) {
				throw new SQLException("Unable to update student record w/ id of " + studentId);
			}
			
		}
		
		return new Student(studentId, student.getFirstName(), student.getLastName(), student.getClassification(), student.getAge());
	}
	
	/*
	 * Exercise for the next 15 minutes: go ahead and implement the deleteStudentById method
	 * 
	 * 1. think about the query you need to write, test it out over on DBeaver, see if it actually works the way you want it to work, and then
	 * 		port that query over to our Java application here
	 * 2. Go through with the same format we have done for updateStudent
	 */
	public void deleteStudentById(int id) throws SQLException {
		
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "DELETE FROM students WHERE student_id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			int numberOfRecordsDeleted = pstmt.executeUpdate();
			
			if (numberOfRecordsDeleted != 1) {
				throw new SQLException("Unable to delete student record w/ id of " + id);
			}
		}
		
	}
	
	public void deleteAllStudents() throws SQLException {
		
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "DELETE FROM students";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			int numberOfRecordsDeleted = pstmt.executeUpdate();
			
			if (numberOfRecordsDeleted == 0) {
				throw new SQLException("Unable to delete any records (check if records exist in the table)");
			}
		}
		
	}
	
}
