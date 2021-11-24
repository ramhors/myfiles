package com.revature.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revature.dto.AddOrUpdateUsersDTO;
import com.revature.model.User;
import com.revature.util.JDBCUtility;

public class UserDAO {

	public User addUsers(AddOrUpdateUsersDTO user) throws SQLException {

		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "INSERT INTO project1.users(user_username,user_password,user_first_name,user_last_name,user_email,user_role)VALUES\r\n"
					+ "(?,?,?,?,?,?)";

			// Setting up the preparedStatement and generate the auto_increment id
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getLastName());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getUserRole());

			// Validation for the number of record inserted
			int numberOfRecordInserted = pstmt.executeUpdate();

			if (numberOfRecordInserted != 1) {
				throw new SQLException("Adding was not succesfull");
			}
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();

			// This key will be for the first column
			int generatedKeyId = rs.getInt(1);
			return new User(generatedKeyId, user.getUserName(), user.getPassword(), user.getFirstName(),
					user.getLastName(), user.getEmail(), user.getUserRole());

		}
	}

	public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "SELECT * FROM project1.users WHERE user_username = ? AND user_password = ?;";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("user_id");
				String user = rs.getString("user_username");
				String pass = rs.getString("user_password");
				String firstName = rs.getString("user_first_name");
				String lastName = rs.getString("user_last_name");
				String email = rs.getString("user_email");
				String userRole = rs.getString("user_role");

				return new User(id, user, pass, firstName, lastName, email, userRole);
			} else {
				return null;
			}
		}
	}

}
