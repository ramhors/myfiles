package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dto.AddOrUpdateReimbursementDTO;
import com.revature.model.Reimbursement;
import com.revature.util.JDBCUtility;

public class ReimbursementDAO {

	java.util.Date utilDate = new java.util.Date();
	java.sql.Timestamp sqlTS = new java.sql.Timestamp(utilDate.getDate());

	public Reimbursement submitReimbursement(AddOrUpdateReimbursementDTO employee) throws SQLException {

		java.sql.Timestamp sqlTS = new java.sql.Timestamp(utilDate.getTime());

		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "INSERT INTO reimbursement(reimb_amount,reimb_date,reimb_type,reimb_description,reimb_author)VALUES\r\n"
					+ "(300,'2021-01-23','Mileage','Mileage for training trip',1)";

			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setTimestamp(1, sqlTS);
			ps.setString(2, employee.getDateResolved());
			ps.setString(3, employee.getStatus());
			ps.setString(4, employee.getType());
			ps.setString(5, employee.getDescription());
			ps.setString(6, employee.getReceipt());
			ps.setInt(7, employee.getAuthor());

			int numberOfRecordInserted = ps.executeUpdate();
			if (numberOfRecordInserted != 1) {
				throw new SQLException("Unsuccesful submission");
			}
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int generatedKeyId = rs.getInt(1);

			return null;
		}
	}

	public List<Reimbursement> getAllReimbursement() throws SQLException {
		try (Connection conn = JDBCUtility.getConnection()) {
			List<Reimbursement> reimbursements = new ArrayList();

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
			List<Reimbursement> reimbursements = new ArrayList();

			String sql = "SELECT * from project1.reimbursement WHERE reimb_id = ?";
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
}
