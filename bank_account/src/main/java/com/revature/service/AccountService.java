package com.revature.service;

import org.slf4j.LoggerFactory;

import com.revature.dao.AccountDAO;
import com.revature.dao.ClientDAO;
import com.revature.dto.AddOrUpdateAccountDTO;
import com.revature.exception.AccountNotFoundException;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.InvalidParameterException;
import com.revature.model.Account;
import com.revature.model.Client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;

public class AccountService {
	
	private Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	private AccountDAO accountDAO;
	
	private ClientDAO clientDao;
	
	
	public AccountService() {
		this.accountDAO = new AccountDAO();
		this.clientDao = new ClientDAO();
	}	
	
		//Setting up a constructor for Mockito
		public AccountService(AccountDAO accountDao, ClientDAO clientDao) {
			this.accountDAO = accountDao;
			this.clientDao = clientDao;
		}
		
	
	//ADDING AN ACCOUNT
	public Account addingAccount(AddOrUpdateAccountDTO dto) throws InvalidParameterException, SQLException, AccountNotFoundException {
		
		if(dto.getAccountBalance() <= 0) {
			throw new InvalidParameterException("Balance can't less or equal 0");
		}
		if(dto.getAccountType().trim().equals("") || dto.getAccountNumber().trim().equals("")) {
			throw new InvalidParameterException("Field can not be blank");
		}
		
		Set<String> validStatus = new HashSet<>();		
		validStatus.add("Active");
		validStatus.add("Inactive");
		validStatus.add("Dormant");
		
		/******
		 * If the status does not contain the information provided in DTO
		 * for the status
		 */
		if(!validStatus.contains(dto.getStatus())) {
			throw new InvalidParameterException("You entered a wrong status value");
		}
		
		//Trimming the leading and trailing whitespace
		dto.setAccountType(dto.getAccountType().trim());
		dto.setAccountBalance(dto.getAccountBalance());
		dto.setAccountNumber(dto.getAccountNumber().trim());
		dto.setClientId(dto.getClientId());
		dto.setStatus(dto.getStatus().trim());
		
		Account insertedAccount = this.accountDAO.addAccount(dto);
		
		return insertedAccount;
	}
	
	/**************************************
	 * Updating the account balance based on 
	 * the account Id	
	 ***************************************/
	public Account updateAccountBalance(String accId, String clId, double accountBalance) throws SQLException, 
	AccountNotFoundException, InvalidParameterException {
		
		try {
			int a_id = Integer.parseInt(accId);
			int c_id = Integer.parseInt(clId);
			
			Account update = this.accountDAO.getAccountById(a_id);
			
			if(update == null) {
				throw new AccountNotFoundException("Account with that id " + a_id + " was no found");
			}
			//Updating the balance in the account
			AddOrUpdateAccountDTO dto = new AddOrUpdateAccountDTO(update.getAccountType(),accountBalance, update.getAccountNumber(),
					update.getClientId(),update.getStatus());
			
			Account updateAccount = this.accountDAO.updateAccounts(a_id,c_id, dto);
			
			return updateAccount;
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Id provided is not an int convertible value");
		}
		
	}
	
	/********************************
	 * Getting all the accounts
	 *******************************/
	public List<Account> getAllAccounts() throws SQLException,AccountNotFoundException, InvalidParameterException {
		logger.info("getAllAcounts() invoked");
		
		List<Account> accounts = this.accountDAO.getAllAccounts();
		
		return accounts;
	}
	
	/************************************
	 * Deleting account By Id
	 *************************/
	public void deleteAccountById(String ac_id) throws SQLException,AccountNotFoundException,
	InvalidParameterException {
		
		try {
			int id = Integer.parseInt(ac_id);
			Account account = this.accountDAO.getAccountById(id);
			if(account == null) {
				throw new AccountNotFoundException("Account with id " + id + "is not found, can't be deleted");
			}
			
		//	this.accountDAO.deleteAccountById(id);
		}catch (NumberFormatException e) {
			throw new InvalidParameterException("Id supplied is not an int");
		}
	}
	
	//Getting account by Client ID
	public List<Account> getAccountByClientId(String clientId) throws SQLException,AccountNotFoundException,
	InvalidParameterException, ClientNotFoundException {
		
		//	List<Account> account = new ArrayList<>();
			
			try {
			int id = Integer.parseInt(clientId);	
			
			//Checking if the client id exists
			Client client = this.clientDao.getClientById(id);
			
			if(client == null) {
				throw new ClientNotFoundException("This client with id " + id + " does not exist");
			}
			//If client exists then get the account
			List<Account> accounts =this.accountDAO.getAccountByClientId(id);
			
			return accounts;
			
		}catch(NumberFormatException e) {
			throw new InvalidParameterException("Client provided is not an int convertiable value");
		}
		
	}
}

