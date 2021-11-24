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
import com.revature.utility.JDBCUtility;

/**********************************************************************
 * 1. Controller layer: contains the controllers that receive the HTTP requests
 * and extract information from the requests. - The controller layer will
 * interact with the Service layer directly 2. Service layer: contains the
 * business logic such as validating proper inputs, processing data, etc. - The
 * service layer will interact with the data access layer directly 3. Data
 * access layer: contains the logic to interact directly with the database - DAO
 * (data access object): contains the methods to perform CRUD operations -
 * Utilize JDBC (Java database connectivity API to do so)
 * 
 * @author rjm20
 *
 ********************************************************************/

public class StudentDao {

	// Adding new student

	public Student addStudent(AddOrUpdateStudentDTO student) throws SQLException {

		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "INSERT INTO students(student_first_name,student_last_name,student_classification,student_age)"
					+ "VALUES(?,?,?,?)";

			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// Return generated keys is used to generate the auto increment id

			pstmt.setString(1, student.getFirstName());
			pstmt.setString(2, student.getLastName());
			pstmt.setString(3, student.getClassification());
			pstmt.setInt(4, student.getAge());

			int numberOfRecordInserted = pstmt.executeUpdate();

			// Checking the number of record inserted, its should be only one, otherwise
			// something is wrong
			if (numberOfRecordInserted != 1) {
				throw new SQLException("Adding a new Student was unsuccesful");
			}
			ResultSet rs = pstmt.getGeneratedKeys();
			// Iterating to the first row
			rs.next();
			int automaticallyGeneratedId = rs.getInt(1); // The number one means it's the first row

			return new Student(automaticallyGeneratedId, student.getFirstName(), student.getLastName(),
					student.getClassification(), student.getAge());

		}
	}

	public List<Student> getAllStudents() throws SQLException {

		List<Student> listOfStudents = new ArrayList<>();
		// Connection conn = JDBCUtility.getConnection();

		// Obtain a Connection object
		try (Connection conn = JDBCUtility.getConnection()) {

			/*******************************************************
			 * From the Connection object, obtain a statement Object (Statement,
			 * PreparedStatement, CallableStatement)
			 */
			String sql = "SELECT * FROM students";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// Here we grab all of the information from the current row that we are on

				int id = rs.getInt("student_id"); // or int id = rs.getInt(1); 1 represent that it's the 1st column
				String firstName = rs.getString("student_first_name"); //
				String lastName = rs.getString("student_last_name");
				String classification = rs.getString("student_classification");
				int age = rs.getInt("student_age");

				// Take the information and create a Student object from that information
				Student s = new Student(id, firstName, lastName, classification, age);
				// Add the student object to the list
				listOfStudents.add(s);
			}

		}
		return listOfStudents;
	}

	public Student getStudentById(int id) throws SQLException {

		try (Connection conn = JDBCUtility.getConnection()) {
			// The '?' is placeholder for arguments that you want to pass into a specific
			// query
			String sql = "SELECT * FROM students WHERE student_id = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			// Since there is only one question mark, we place the value of id variable into
			// the 1st question mark
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new Student(rs.getInt("student_id"), rs.getString("student_first_name"),
						rs.getString("student_last_name"), rs.getString("student_classification"),
						rs.getInt("student_age"));
			} else {
				return null;
			}
		}
	}

	public Student updateStudent(int studentId, AddOrUpdateStudentDTO student) throws SQLException {

		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "UPDATE students "
					+ "SET student_first_name = ?,"
					+ "	student_last_name  = ?,"
					+ "	student_classification  = ?,"
					+ "	student_age  = ?"
					+ "	WHERE student_id = ?;";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, student.getFirstName());
			pstmt.setString(2, student.getLastName());
			pstmt.setString(3, student.getClassification());
			pstmt.setInt(4, student.getAge());
			pstmt.setInt(5, studentId);
			
			int numberOfRecordsUpdated = pstmt.executeUpdate();
			
			if(numberOfRecordsUpdated != 1) {
				throw new SQLException("Unable to update student record with id of " + studentId);
			}
			
		}
		return new Student(studentId, student.getFirstName(), student.getLastName(), student.getClassification(), student.getAge());
		
	}

	public void deleteStudentById(int id) throws SQLException {
		
		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "DELETE FROM students WHERE student_id = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			// Since there is only one question mark, we place the value of id variable into
			// the 1st question mark
			pstmt.setInt(1, id);

			int numberOfRecordDeleted = pstmt.executeUpdate();

			if (numberOfRecordDeleted != 1) {
				throw new SQLException ("Unable to delete a record with id of" + id);
			}

		}
	}

	public void deleteAllStudents() throws SQLException {
		try (Connection conn = JDBCUtility.getConnection()) {
			
			String sql = "DELETE FROM students";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			
			int numberOfRecordDeleted = pstmt.executeUpdate();
			
			if(numberOfRecordDeleted == 0) {
				throw new SQLException("Unable to delete any record (check if record exist in the table");
			}
			
		}
		
	}

}
