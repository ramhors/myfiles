package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.revature.dto.AddOrUpdateReimbursementDTO;
import com.revature.model.Reimbursement;
import com.revature.util.JDBCUtility;

public class ReimbursementDAO {
	
	 java.util.Date utilDate = new java.util.Date();
	 java.sql.Timestamp sqlTS = new java.sql.Timestamp(utilDate.getDate());
	
	public Reimbursement submitReimbursement(AddOrUpdateReimbursementDTO employee) throws SQLException {
		
		java.sql.Timestamp sqlTS = new java.sql.Timestamp(utilDate.getTime());
		
		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "INSERT into reimbursement(reimb_submitted,reimb_resolved ,reimb_status,reimb_type ,reimb_description,reimb_receipt ,reimb_author)VALUES"
					+ "(?,?,?,?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setTimestamp(1, sqlTS);
			ps.setString(2, employee.getDateResolved());
			ps.setString(3, employee.getStatus());
			ps.setString(4, employee.getType());
			ps.setString(5, employee.getDescription());
			ps.setString(6, employee.getReceipt());
			ps.setInt(7, employee.getAuthor());
			
			
			int numberOfRecordInserted =pstmt.executeUpdate();
			if(numberOfRecordInserted != 1) {
				throw new SQLException("Unsuccesful submission");
			}
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			int generatedKeyId = rs.getInt(1);
			
			
		}
	}
}
