package com.revature.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dto.AddOrUpdateAccountDTO;
import com.revature.exception.AccountNotFoundException;
import com.revature.exception.InvalidParameterException;
import com.revature.model.Account;
import com.revature.utility.JDBCUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAO {

	// Adding an account
	public Account addAccount(AddOrUpdateAccountDTO account)
			throws SQLException, AccountNotFoundException, InvalidParameterException {

		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "INSERT INTO account(account_type,account_balance,account_number,client_id,status)VALUES(?,?,?,?,?)";

			// Setting up the prepared Statement
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, account.getAccountType());
			pstmt.setDouble(2, account.getAccountBalance());
			pstmt.setString(3, account.getAccountNumber());
			pstmt.setInt(4, account.getClientId());
			pstmt.setString(5, account.getStatus());

			// Verifying the number of account inserted
			int numberOfRecordInserted = pstmt.executeUpdate();
			if (numberOfRecordInserted != 1) {
				throw new SQLException("Adding account was not succesful");
			}

			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			// getInt(1) is indicating that it's the first row
			int generatedKeyId = rs.getInt(1);

			return new Account(generatedKeyId, account.getAccountType(), account.getAccountBalance(),
					account.getAccountNumber(), account.getClientId(), account.getStatus());
		}

	}

	// Updating Account
	public Account updateAccount(int accountId, AddOrUpdateAccountDTO account)
			throws SQLException, AccountNotFoundException, InvalidParameterException {

		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "UPDATE account " + "SET account_type = ?," + "	account_balance = ?,"
					+ "	account_number = ?," + "	client_id = ?," + "	status = ?" + "WHERE account_id = ?;";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, account.getAccountType());
			pstmt.setDouble(2, account.getAccountBalance());
			pstmt.setString(3, account.getAccountNumber());
			pstmt.setInt(4, account.getClientId());
			pstmt.setString(5, account.getStatus());
			pstmt.setInt(6, accountId);

			int numberOfRecordUpdated = pstmt.executeUpdate();

			if (numberOfRecordUpdated != 1) {
				throw new SQLException("Unable to update the account with id of " + accountId);
			}
		}
		return new Account(accountId, account.getAccountType(), account.getAccountBalance(), account.getAccountNumber(),
				account.getClientId(), account.getStatus());
	}

	// UPDATING ACCOUNT
	public Account updateAccounts(int accId, int clId, AddOrUpdateAccountDTO account)
			throws SQLException, AccountNotFoundException, InvalidParameterException {

		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "UPDATE account " + "SET account_type = ?," + "	account_balance = ?,"
					+ "	account_number = ?," + "	status = ?" + "WHERE account_id = ? AND client_id = ?;";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, account.getAccountType());
			pstmt.setDouble(2, account.getAccountBalance());
			pstmt.setString(3, account.getAccountNumber());
			pstmt.setString(4, account.getStatus());
			pstmt.setInt(5, accId);
			pstmt.setInt(6, clId);

			int numberOfRecordUpdated = pstmt.executeUpdate();

			if (numberOfRecordUpdated != 1) {
				throw new SQLException("Unable to update the account with id of " + accId);
			}
		}
		return new Account(accId, account.getAccountType(), account.getAccountBalance(), account.getAccountNumber(),
				clId, account.getStatus());
	}

	public List<Account> getAllAccounts() throws SQLException, AccountNotFoundException, InvalidParameterException {
		List<Account> listOfAccounts = new ArrayList<>();

		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "SELECT * from account";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// Getting all the information on the current row
				int id = rs.getInt("account_id");
				String accountType = rs.getString("account_type");
				double accountBalance = rs.getDouble("account_balance");
				String accountNumber = rs.getString("account_number");
				int clientId = rs.getInt("client_id");
				String status = rs.getString("status");

				// Take the information and create a Client object from the information
				Account account = new Account(id, accountType, accountBalance, accountNumber, clientId, status);

				// Adding the client to the list
				listOfAccounts.add(account);
			}
		}
		return listOfAccounts;
	}

	// Getting account by client Id
	
	public List<Account> getAccountByClientId(int id)
			throws SQLException, AccountNotFoundException, InvalidParameterException {

		List<Account> accountList = new ArrayList<>();

		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "SELECT * FROM account where client_id = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int a_id = rs.getInt("account_id");
				String type = rs.getString("account_type");
				double balance = rs.getDouble("account_balance");
				String accountNumber = rs.getString("account_number");
				int clientId = rs.getInt("client_id");
				String status = rs.getString("status");
				
				Account account = new Account(a_id, type, balance, accountNumber,clientId, status);

				accountList.add(account);
			}
		}
		return accountList;
	}

	// Getting account by account id
	public Account getAccountById(int id) throws SQLException,AccountNotFoundException,
	InvalidParameterException {		
		
		
		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "SELECT * from account where account_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return new Account(rs.getInt("account_id"),rs.getString("account_type"),rs.getDouble("account_balance"),
				rs.getString("account_number"),rs.getInt("client_id"),rs.getString("status"));				
			}else {
				return null;
			}		
		}
	}

	// Delete account by id
	public void deleteAccountById(int accountId) throws SQLException, AccountNotFoundException, InvalidParameterException {

		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "DELETE FROM account WHERE account_id = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, accountId);

			pstmt.executeUpdate();
			
		}
	}

	// Getting all the Accounts
	public List<Account> getAllAcounts() throws SQLException, AccountNotFoundException, InvalidParameterException {
		List<Account> listOfAccounts = new ArrayList<>();

		try (Connection conn = JDBCUtility.getConnection()) {
			String sql = "SELECT * from account";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("account_id");
				String accountType = rs.getString("account_type");
				double accountBalance = rs.getDouble("account_balance");
				String accountNumber = rs.getString("account_number");
				int clientId = rs.getInt("client_id");
				String status = rs.getString("status");

				// Creating an Account object
				Account account = new Account(id, accountType, accountBalance, accountNumber, clientId, status);

				// Adding the client object to the list
				listOfAccounts.add(account);
			}
		}
		return listOfAccounts;
	}

}
