package com.revature.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dto.AddReimbursementDTO;
import com.revature.exception.ReimbursementAlreadyResolvedException;
import com.revature.exception.ReimbursementNotFound;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.util.JDBCUtility;

public class ReimbursementDAO {
	
	Reimbursement reimbursement = new Reimbursement();
	
	public Reimbursement submitReimbursement(AddReimbursementDTO reimbs, int authorId) throws SQLException {
		
		try (Connection conn = JDBCUtility.getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO project1.reimbursement(reimb_amount,reimb_type,reimb_description,reimb_date)VALUES(?,?,?,now())";
			
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setDouble(1, reimbursement.getAmount());
			ps.setString(2, reimbursement.getType());
			ps.setString(3, reimbursement.getDescription());
			
			int numberOfRecordInserted = ps.executeUpdate(); 	
			
			if (numberOfRecordInserted != 1) {
				throw new SQLException("There was an issue when adding reimbursement");
			}
			ResultSet rs = ps.getGeneratedKeys();
			
			rs.next();			
			int generatedKeyId = rs.getInt(1);
			
			conn.commit();
			
			return new Reimbursement( generatedKeyId,reimbs.getAmount(),reimbs.getType(),reimbs.getDescription(),authorId,rs.getString("reimb_date"));
	}
}
	
	public void updateWithReceipt(int reimbursementId,InputStream image) throws SQLException {
		try (Connection conn = JDBCUtility.getConnection()) {			
			
		String sql = "UPDATE project1.reimbursement "
				+ "SET reimb_receipt = ? "
				+ "WHERE reimb_id = ?;";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setBinaryStream(1, image);
		ps.setInt(2, reimbursementId);
		
		int numberOfInsertedRecords = ps.executeUpdate();
		
		if(numberOfInsertedRecords != 1) {
			throw new SQLException("Issue occured when adding reimbursement");
		}
		
	}
}	
	
	public InputStream getReceiptFromReimbursementById(int id) throws SQLException {
		try(Connection conn = JDBCUtility.getConnection()) {
			String sql = "select reimb_receipt from project1.reimbursement WHERE reimb_id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);			
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				InputStream image = rs.getBinaryStream("reimb_receipt");
				System.out.println(image);
				return image;
			}
			return null;
		}
	}
	
	public Reimbursement addReimbursement(double amount,String reimbursementType, String description, int authorId, InputStream image) throws SQLException {
		
			try (Connection conn = JDBCUtility.getConnection()) {
			Reimbursement reimbursement = new Reimbursement();
			conn.setAutoCommit(false);			
		
			String sql = "INSERT INTO reimbursement(reimb_amount,reimb_date,reimb_type,reimb_description,reimb_author,reimb_receipt)VALUES(?,now(),?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setDouble(1, amount);
			ps.setString(2, reimbursementType);
			ps.setString(3, description);
			ps.setInt(4, authorId);
			ps.setBinaryStream(5, image);
			

			int numberOfRecordInserted = ps.executeUpdate(); 	
			
			if (numberOfRecordInserted != 1) {
				throw new SQLException("There was an issue when adding reimbursement");
			}
			
			ResultSet rs = ps.getGeneratedKeys();
			
			rs.next();			
			int generatedKeyId = rs.getInt(1);
			
			conn.commit();

			return new Reimbursement(generatedKeyId,amount,reimbursementType,description,authorId,rs.getString("reimb_date"));
		}
	}

	public List<Reimbursement> getAllReimbursement() throws SQLException {
		try (Connection conn = JDBCUtility.getConnection()) {
			List<Reimbursement> reimbursements = new ArrayList<>();

			String sql = "SELECT * from project1.reimbursement";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("reimb_id");
				double amount = rs.getDouble("reimb_amount");
				String submitDate = rs.getString("reimb_date");
				String resolvedDate = rs.getString("reimb_resolved");
				String status = rs.getString("reimb_status");
				String type = rs.getString("reimb_type");
				String desc = rs.getString("reimb_description");
				String receipt = rs.getString("reimb_receipt");
				int authorId = rs.getInt("reimb_author");
				int resolver = rs.getInt("reimb_resolver");

				Reimbursement reimbursement = new Reimbursement(id, amount, submitDate, resolvedDate, status, type,
						desc, receipt, authorId, resolver);

				reimbursements.add(reimbursement);
			}
			return reimbursements;
		}
	}

	public List<Reimbursement> getAllReimbursementByEmployee(int employeeId) throws SQLException {
		try (Connection conn = JDBCUtility.getConnection()) {
			List<Reimbursement> reimbursements = new ArrayList<>();

			String sql = "SELECT * from project1.reimbursement WHERE reimb_author = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, employeeId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("reimb_id");
				double amount = rs.getDouble("reimb_amount");
				String submitDate = rs.getString("reimb_date");
				String resolvedDate = rs.getString("reimb_resolved");
				String status = rs.getString("reimb_status");
				String type = rs.getString("reimb_type");
				String desc = rs.getString("reimb_description");
				String receipt = rs.getString("reimb_receipt");
				int authorId = rs.getInt("reimb_author");
				int resolver = rs.getInt("reimb_resolver");

				Reimbursement reimbursement = new Reimbursement(id, amount, submitDate, resolvedDate, status, type,
						desc, receipt, authorId, resolver);

				reimbursements.add(reimbursement);
			}
			return reimbursements;
		}
	}
	public Reimbursement getReimbursementByItsId(int reimbursementId) throws SQLException {
		try (Connection conn = JDBCUtility.getConnection()) {
			
			String sql ="SELECT * from project1.reimbursement WHERE reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimbursementId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int id = rs.getInt("reimb_id");
				double amount = rs.getDouble("reimb_amount");
				String submitDate = rs.getString("reimb_date");
				String resolvedDate = rs.getString("reimb_resolved");
				String status = rs.getString("reimb_status");
				String type = rs.getString("reimb_type");
				String desc = rs.getString("reimb_description");
				String receipt = rs.getString("reimb_receipt");
				int authorId = rs.getInt("reimb_author");
				int resolver = rs.getInt("reimb_resolver");
				
				return new Reimbursement(id,amount,submitDate,resolvedDate,status,type,desc,receipt,authorId,resolver);
			}else {
				return null;
			}
		}
	}
	
	public void updateReimbursement(int reimbursementId, String status, int userId) throws SQLException {
		Reimbursement reimbursement = new Reimbursement();
		
		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "UPDATE project1.reimbursement "
					+ "SET reimb_status = ?, "
					+ "reimb_resolved = now(), "
					+ "reimb_resolver = ? "
					+ "where reimb_id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
		
			ps.setString(1, status);			
			ps.setInt(2, userId);
			ps.setInt(3, reimbursementId);
			
			int updateCount = ps.executeUpdate();
			
			if(updateCount != 1) {
				throw new SQLException("Something occurred when trying to update reimbursement");
			}
		}
	}
}
