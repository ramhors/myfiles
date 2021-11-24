package com.revature.service;

import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;

import com.revature.dao.AccountDAO;
import com.revature.dao.ClientDAO;
import com.revature.dto.AddOrUpdateClientDTO;
import com.revature.exception.AccountNotFoundException;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.InvalidParameterException;
import com.revature.model.Client;

public class ClientService {

	private Logger logger = LoggerFactory.getLogger(ClientService.class);

	private ClientDAO clientDAO;
//	private AccountDAO accountDao;

	// Creating a real clientDAO object for ClientService object
	public ClientService() {
		this.clientDAO = new ClientDAO();
//		this.accountDao = new AccountDAO();
	}
	
	public ClientService(ClientDAO clientDao) {
		this.clientDAO = clientDao;
	}


	/*******************
	 * Updating first name of the client this method will first grab the Client with
	 * the particular client id from the database
	 *****************************************************************************************/

	public Client editFirstNameOfClient(String clientId, String changedFirstName) throws SQLException, ClientNotFoundException, InvalidParameterException {

		/**************************
		 * Converting String to int
		 *************************/
		try {
			int id = Integer.parseInt(clientId);

			// Grabbing the information about the client with an id of the value clientId
			Client clientToEdit = this.clientDAO.getClientById(id);

			if (clientToEdit == null) {
				throw new ClientNotFoundException("Client with an id of " + clientId + " was no found");
			}
			// Changing the firstName in this DTO and leave the rest the same
			AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO(changedFirstName, clientToEdit.getLastName(),
					clientToEdit.getPhoneNumber(), clientToEdit.getClientAge());

			Client updateClient = this.clientDAO.updateClient(id, dto);

			return updateClient;
			
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Id provided is not an int convertable value");
		}
	}

	/***************************************
	 * Updating the lastName of the client
	 ****************************************/

	public Client editLastNameOfClient(String clientId, String changedLastName)
			throws SQLException, ClientNotFoundException, InvalidParameterException {

		/**************************
		 * Converting String to int
		 *************************/
		try {
			int id = Integer.parseInt(clientId);

			// Grabbing the information about the client with an id of the value clientId
			Client clientToEdit = this.clientDAO.getClientById(id);

			if (clientToEdit == null) {
				throw new ClientNotFoundException("Client with an id of " + clientId + " was no found");
			}
			// Changing the firstName in this DTO and leave the rest the same
			AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO(clientToEdit.getFirstName(), changedLastName,
					clientToEdit.getPhoneNumber(), clientToEdit.getClientAge());

			Client updateClient = this.clientDAO.updateClient(id, dto);

			return updateClient;
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Id provided is not an int convertable value");
		}
	}

	/******************************************
	 * Updating the phoneNumber of the client
	 ********************************************/

	public Client editPhoneNumberOfClient(String clientId, String changedPhoneNumber)
			throws SQLException, ClientNotFoundException, InvalidParameterException {

		/******************************
		 * Converting String to int
		 *************************/
		try {
			int id = Integer.parseInt(clientId);

			// Grabbing the information about the client with an id of the value clientId
			Client clientToEdit = this.clientDAO.getClientById(id);

			if (clientToEdit == null) {
				throw new ClientNotFoundException("Client with an id of " + clientId + " was no found");
			}
			// Changing the firstName in this DTO and leave the rest the same
			AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO(clientToEdit.getFirstName(), clientToEdit.getLastName(),
					changedPhoneNumber, clientToEdit.getClientAge());

			Client updateClient = this.clientDAO.updateClient(id, dto);

			return updateClient;
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Id provided is not an int convertable value");
		}
	}

	/*******************************************
	 * Getting all of the client from the DAO layer
	 * 
	 * @throws SQLException
	 ********************************************/
	public List<Client> getAllClient() throws SQLException {
		logger.info("getAllClients() invoked");

		List<Client> clients = this.clientDAO.getAllClients();

		return clients;
	}

	// Getting client by ID
	public Client getClientById(String clientId)
			throws SQLException, InvalidParameterException, ClientNotFoundException {
		// converting from a string to an int
		try {
			int id = Integer.parseInt(clientId);

			Client client = this.clientDAO.getClientById(id);

			if (client == null) {
				throw new ClientNotFoundException("Client with id of " + clientId + " was not found");
			}

			return client;
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Id provided is not an int convertable value");
		}
	}

	/*********************************************************
	 * Business Logic - Adding student firstName,lastName,phoneNumber can not be
	 * blank Age can not be negative or less than 18
	 ***********************************************************/

	public Client addClient(AddOrUpdateClientDTO dto) throws InvalidParameterException, SQLException {

		/****************************************************************
		 * This dto will contain firstName,lastName,phoneNumber and age Also checking if
		 * the fields are empty.
		 ***********************************************************************/
		if (dto.getFirstName().trim().equals("") || dto.getLastName().trim().equals("")
				|| dto.getPhoneNumber().trim().equals("")) {
			throw new InvalidParameterException("Field can not be blank");
		}
		// Age can not be negative or lesse than 18
		if (dto.getClientAge() < 0) {
			throw new InvalidParameterException("Age cannot be lessa than 0");
		}
		if (dto.getClientAge() < 18) {
			throw new InvalidParameterException("You have to be 18 or over to have an account");
		}
		// Trimming the leading and trailing whitespace
		dto.setFirstName(dto.getFirstName().trim());
		dto.setLastName(dto.getLastName().trim());
		dto.setPhoneNumber(dto.getPhoneNumber().trim());

		Client insertedClient = this.clientDAO.addClient(dto);

		return insertedClient;
	}

	/*****************************************************************
	 * Deleting student and checking if the studentId provided in the URL is
	 * actually an it, and if not, throw an InvalidParameterException. If the
	 * student we are trying to delete does not exist, throw
	 * StudentNotFoundException
	 * @throws AccountNotFoundException 
	 *********************************************************************/

	public void deleteClientById(String clientId)
			throws InvalidParameterException, SQLException, ClientNotFoundException, AccountNotFoundException {
		try {
			int id = Integer.parseInt(clientId);
			// Checking the client with the id exists or not
			Client client = this.clientDAO.getClientById(id);
			if (client == null) {
				throw new ClientNotFoundException(
						"Client with id " + id + " was not found, and therefore can not be deleted");
			}
			
			//Delete all account that belongs to the client
		//	this.accountDao.deleteAccountByClientId(id);

			/*****
			 * This will delete all client account belongs by the client and will make it possible 
			 * to delete the client too.
			 */
			this.clientDAO.deleteClientById(id);
			
		} catch (NumberFormatException e) {
			throw new InvalidParameterException("Id supplied is not an int");
		}
	}
	//Edit all client information except the age
	
	public Client editClientById(String clientId, String changedFirstName, String changedLastName,
			String changedPhoneNumber) throws SQLException, ClientNotFoundException,InvalidParameterException {
		
		try {
			int id = Integer.parseInt(clientId);
			Client clientToEdit = this.clientDAO.getClientById(id);
			
			if(clientToEdit == null) {
				throw new ClientNotFoundException("Client with an id of " + clientId + "was not found");
			}
		//Adding the change in the DTO
			AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO(changedFirstName,changedLastName,changedPhoneNumber,
					clientToEdit.getClientAge());
			
			Client updatedClient = this.clientDAO.updateClient(id, dto);
			
			return updatedClient;
			
		}catch(NumberFormatException e) {
			throw new InvalidParameterException("id provided is not an int convertable value");
		}
	}
}
