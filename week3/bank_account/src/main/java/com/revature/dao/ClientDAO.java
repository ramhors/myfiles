package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dto.AddOrUpdateClientDTO;
import com.revature.model.Client;
import com.revature.utility.JDBCUtility;

public class ClientDAO {

	public Client addClient(AddOrUpdateClientDTO client) throws SQLException {

		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "INSERT INTO clients(first_name,last_name,phone_number,client_age)VALUES(?,?,?,?)";

			// Setting up PrepareStetament and generate the auto increment id
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, client.getFirstName());
			pstmt.setString(2, client.getLastName());
			pstmt.setString(3, client.getPhoneNumber());
			pstmt.setInt(4, client.getClientAge());

			// Checking the number of customer inserted
			int numberOfRecordInserted = pstmt.executeUpdate();
			if (numberOfRecordInserted != 1) {
				throw new SQLException("Client was unsuccesfully added");
			}
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			int generatedKeyId = rs.getInt(1); // For the first row

			return new Client(generatedKeyId, client.getFirstName(), client.getLastName(), client.getPhoneNumber(),
					client.getClientAge());
		}
	}

	public List<Client> getAllClients() throws SQLException {
		List<Client> listOfClients = new ArrayList<>();

		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "SELECT * from clients";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// Getting all the information on the current row
				int id = rs.getInt("client_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_Name");
				String phoneNumber = rs.getString("phone_number");
				int age = rs.getInt("client_age");

				// Take the information and create a Client object from that information
				Client client = new Client(id, firstName, lastName, phoneNumber, age);

				// Adding the client object to the list"
				listOfClients.add(client);
			}
		}

		return listOfClients;
	}

	// Getting student by their Id
	public Client getClientById(int id) throws SQLException {

		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "SELECT * FROM clients WHERE client_id = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new Client(rs.getInt("client_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("phone_number"), rs.getInt("client_age"));
			} else {
				return null;
			}
		}
	}

	// Updating Clients
	public Client updateClient(int clientId, AddOrUpdateClientDTO client) throws SQLException {
		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "UPDATE clients " + "SET first_name = ?," + "	last_name = ?," + "	phone_number = ?,"
					+ "	client_age = ?" + "WHERE client_id = ?;";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, client.getFirstName());
			pstmt.setString(2, client.getLastName());
			pstmt.setString(3, client.getPhoneNumber());
			pstmt.setInt(4, client.getClientAge());
			pstmt.setInt(5, clientId);

			int numberOfRecordUpdated = pstmt.executeUpdate();

			if (numberOfRecordUpdated != 1) {
				throw new SQLException("Unable to update customer record with id of " + clientId);
			}
		}
		return new Client(clientId, client.getFirstName(), client.getLastName(), client.getPhoneNumber(),
				client.getClientAge());
	}

	// Delete client by Id
	public void deleteClientById(int id) throws SQLException {

		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "DELETE FROM clients WHERE client_id = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int numberOfRecordDeleted = pstmt.executeUpdate();

			if (numberOfRecordDeleted != 1) {
				throw new SQLException("Unable to delete a record with id of" + id);

			}
		}
	}

	// Delete All clients
	public void deleteAllClients() throws SQLException {
		try (Connection conn = JDBCUtility.getConnection()) {

			String sql = "DELETE FROM clients";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			int numberOfRecordDeleted = pstmt.executeUpdate();

			if (numberOfRecordDeleted == 0) {
				throw new SQLException("Unable to delete the records");
			}
		}
	}

}
