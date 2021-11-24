package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Grade;
import com.revature.util.JDBCUtility;

public class GradeDAO {

	public void deleteAllGradesByStudentId(int studentId) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "DELETE FROM grades WHERE student_id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, studentId);
			
			pstmt.executeUpdate();
		}
	}
	
	public void deleteGradeById(int gradeId) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "DELETE FROM grades WHERE grade_id = ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gradeId);
			
			pstmt.executeUpdate();
		}
	}
	
	public List<Grade> getAllGradesByStudentId(int studentId, int greaterThan, int lessThan) throws SQLException {
		List<Grade> grades = new ArrayList<>();
		
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "SELECT * FROM grades WHERE student_id = ? AND grade >= ? AND grade <= ?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, studentId);
			pstmt.setInt(2, greaterThan);
			pstmt.setInt(3, lessThan);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("grade_id");
				int grade = rs.getInt("grade");
				String assignmentName = rs.getString("assignment_name");
				
				Grade g = new Grade(id, grade, assignmentName);
				
				grades.add(g);
			}
		}
		
		return grades;
	}
	
}
