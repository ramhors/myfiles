package com.revature.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.revature.dao.ClientDAO;
import com.revature.dto.AddOrUpdateClientDTO;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.InvalidParameterException;
import com.revature.model.Client;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientServiceTest {

	private ClientService sut;

	@Test
	public void testGetAllClientPositive() throws SQLException {

		ClientDAO mockClientDao = mock(ClientDAO.class);

		Client client1 = new Client(4, "Patrick", "Bob", "717-758-1254", 55);
		Client client2 = new Client(5, "Patricia", "Lou", "717-122-1254", 36);
		Client client3 = new Client(6, "Narcisse", "Rakoto", "800-758-1924", 55);

		List<Client> clientsFromDao = new ArrayList<>();
		clientsFromDao.add(client1);
		clientsFromDao.add(client2);
		clientsFromDao.add(client3);

		when(mockClientDao.getAllClients()).thenReturn(clientsFromDao);

		ClientService clientService = new ClientService(mockClientDao);

		// ACT
		List<Client> actual = clientService.getAllClient();

		// ASSERT
		List<Client> expected = new ArrayList<>();
		expected.add(new Client(4, "Patrick", "Bob", "717-758-1254", 55));
		expected.add(new Client(5, "Patricia", "Lou", "717-122-1254", 36));
		expected.add(new Client(6, "Narcisse", "Rakoto", "800-758-1924", 55));

		Assertions.assertEquals(expected, actual);
	}

	// Negative test
	@Test
	public void testGetAllClientsSQLExceptionOnOccursNegative() throws SQLException {

		ClientDAO mockClientDao = mock(ClientDAO.class);

		when(mockClientDao.getAllClients()).thenThrow(SQLException.class);

		ClientService clientService = new ClientService(mockClientDao);

		Assertions.assertThrows(SQLException.class, () -> {

			clientService.getAllClient();
		});

	}
	// Positive test for Adding client

	@Test
	public void testAddClientAllInformationCorrectInDTO()
			throws SQLException, InvalidParameterException, ClientNotFoundException {

		ClientDAO clientDao = mock(ClientDAO.class);

		AddOrUpdateClientDTO dtoIntoDao = new AddOrUpdateClientDTO("Steve", "Stephen", "717-852-1542", 34);

		when(clientDao.addClient(eq(dtoIntoDao))).thenReturn(new Client(5, "Steve", "Stephen", "717-852-1542", 34));

		ClientService clientService = new ClientService(clientDao);

		// ACT
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Steve", "Stephen", "717-852-1542", 34);
		Client actual = clientService.addClient(dto);

		// ASSERT
		Client expected = new Client(5, "Steve", "Stephen", "717-852-1542", 34);
		Assertions.assertEquals(expected, actual);

	}

	/***************
	 * NEGATIVE TEST Scenario: Everything is correct except the lastName was left
	 * blank
	 */
	@Test
	public void testAddStudentLasstNameBlankEverythingElseValid()
			throws SQLException, InvalidParameterException, ClientNotFoundException {

		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);

		// ACT AD ASSERT
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Steve", "   ", "717-852-1542", 34);

		Assertions.assertThrows(InvalidParameterException.class, () -> {

			clientService.addClient(dto);

		});
	}

	/***************
	 * NEGATIVE TEST Scenario: Everything is correct except the firstName was left
	 * blank
	 */
	@Test
	public void testAddStudentfirstNameBlankEverythingElseValid()
			throws SQLException, InvalidParameterException, ClientNotFoundException {

		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);

		// ACT AD ASSERT
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("  ", "Steve", "717-852-1542", 34);

		Assertions.assertThrows(InvalidParameterException.class, () -> {

			clientService.addClient(dto);

		});
	}

	/***************
	 * NEGATIVE TEST Scenario: Everything is correct except the phone Number was
	 * left blank
	 */
	@Test
	public void testAddStudentPhoneNumberBlankEverythingElseValid()
			throws SQLException, InvalidParameterException, ClientNotFoundException {

		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);

		// ACT AD ASSERT
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Steve", "Bob", "     ", 34);

		Assertions.assertThrows(InvalidParameterException.class, () -> {

			clientService.addClient(dto);

		});
	}

	/***************
	 * NEGATIVE TEST Scenario: Everything is correct except the Age is negative
	 */
	@Test
	public void testAddStudentAgeBlankEverythingElseValid()
			throws SQLException, InvalidParameterException, ClientNotFoundException {

		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);

		// ACT AD ASSERT
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Steve", "Bob", "717-245-5698", -12);

		Assertions.assertThrows(InvalidParameterException.class, () -> {

			clientService.addClient(dto);

		});
	}

	/***************
	 * NEGATIVE TEST Scenario: Everything is correct except both firstName and last
	 * name left blank
	 */
	@Test
	public void testAddStudentFirstNameAndLastNameBlankEverythingElseValid()
			throws SQLException, InvalidParameterException, ClientNotFoundException {

		ClientDAO clientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(clientDao);

		// ACT AD ASSERT
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("   ", "   ", "717-245-5698", 0);

		Assertions.assertThrows(InvalidParameterException.class, () -> {

			clientService.addClient(dto);

		});
	}

	// Positive test for editing first name
	@Test
	public void testEditFirstNameOfClientPositive()
			throws SQLException, InvalidParameterException, ClientNotFoundException {

		/**
		 * ARRANGE
		 ************/
		ClientDAO mockClientDao = mock(ClientDAO.class);

		when(mockClientDao.getClientById(eq(5))).thenReturn(new Client(5, "Steve", "Stephen", "717-852-1542", 34));

		AddOrUpdateClientDTO dtoIntoDao = new AddOrUpdateClientDTO("Patrick", "Stephen", "717-852-1542", 34);
		when(mockClientDao.updateClient(eq(5), eq(dtoIntoDao)))
				.thenReturn(new Client(5, "Patrick", "Stephen", "717-852-1542", 34));

		ClientService clientService = new ClientService(mockClientDao);

		// ACT
		Client actual = clientService.editFirstNameOfClient("5", "Patrick");

		// ASSERT
		Client expected = new Client(5, "Patrick", "Stephen", "717-852-1542", 34);

		Assertions.assertEquals(expected, actual);
	}

	// NEGATIVE TEST FOR EDIT FIRSTNAME
	@Test
	public void testEditFirstNameOfClientButClientWithId5DoesNotExist() {

		ClientDAO mockClientDao = mock(ClientDAO.class);

		ClientService clientService = new ClientService(mockClientDao);

		// ACT and ASSERT

		Assertions.assertThrows(ClientNotFoundException.class, () -> {

			clientService.editFirstNameOfClient("5", "George");

		});

	}
	
	//Negative Test - InvalidParameterException thrown
	
		@Test
		public void testEditFirstNameButIdProvidedIsNotAnInt() {
			
			ClientDAO mockClientDao = mock(ClientDAO.class);
			
			ClientService clientService = new ClientService(mockClientDao);
			
			//ACT and ASSERT
			
			Assertions.assertThrows(InvalidParameterException.class, () -> {
				
				clientService.editFirstNameOfClient("sfssdfbs", "George");
				
			});
		}

	// Positive test for editing LastName
	@Test
	public void testEditLastNameOfClientPositive()
			throws SQLException, InvalidParameterException, ClientNotFoundException {

		ClientDAO mockClientDao = mock(ClientDAO.class);

		when(mockClientDao.getClientById(eq(11))).thenReturn(new Client(11, "Steve", "Klem", "800-458-4512", 42));

		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Steve", "Bob", "800-458-4512", 42);

		when(mockClientDao.updateClient(eq(11), eq(dto)))
				.thenReturn(new Client(11, "Steve", "Bob", "800-458-4512", 42));

		ClientService clientService = new ClientService(mockClientDao);

		// ACT
		Client actual = clientService.editLastNameOfClient("11", "Bob");

		// ASSERT
		Client expected = new Client(11, "Steve", "Bob", "800-458-4512", 42);

		Assertions.assertEquals(expected, actual);

	}
	
	//Negative test for editing last name
		@Test
		public void testEditLastNameOfClientButClientWithId11DoesNotExist() {
			
			ClientDAO mockClientDao = mock(ClientDAO.class);
			
			ClientService clientService = new ClientService(mockClientDao);
			
			//ACT and ASSERT
			
			Assertions.assertThrows(ClientNotFoundException.class, () ->  {
				
				clientService.editLastNameOfClient("11", "Bob");
			});
		}
		
		//Positive Test for editing Phone number
		@Test
		public void testEditPhoneNumberOfClient() throws SQLException, InvalidParameterException, ClientNotFoundException {
			
			ClientDAO mockClientDao = mock(ClientDAO.class);
			
			when(mockClientDao.getClientById(eq(8))).thenReturn(new Client(8, "Suzan", "Barb", "717-124-9875", 23));
			
			AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Suzan", "Barb", "800-245-4175",23);
			
			when(mockClientDao.updateClient(eq(8), eq(dto))).thenReturn(new Client(8, "Suzan", "Barb", "800-245-4175",23));
			
			ClientService clientService = new ClientService(mockClientDao);
			
			//ACT
			Client actual = clientService.editPhoneNumberOfClient("8", "800-245-4175");
			
			//ASSERT
			Client expected = new Client(8, "Suzan", "Barb", "800-245-4175",23);
			
			Assertions.assertEquals(expected, actual);
		}
	
		//Negative test edit Phone number
		@Test
		public void testEditPhoneNumberOfClientButClientWithId8DoesNotExist() {
			
			ClientDAO mockClientDao = mock(ClientDAO.class);
			
			ClientService clientService = new ClientService(mockClientDao);
			
			//ACT and ASSERT
			
			Assertions.assertThrows(ClientNotFoundException.class, () -> {
				
				clientService.editPhoneNumberOfClient("8","800-245-4175");
			});
		}
		
		// Positive Test for client by Id
		@Test
		public void testGetClientByIdPositive() throws SQLException, InvalidParameterException, ClientNotFoundException {

			ClientDAO mockClientDao = mock(ClientDAO.class);

			when(mockClientDao.getClientById(eq(5))).thenReturn(new Client(5, "Jean", "Steph", "717-789-4594", 34));

			ClientService clientService = new ClientService(mockClientDao);

			// ACT
			Client actual = clientService.getClientById("5");

			// ASSERT
			Assertions.assertEquals(new Client(5, "Jean", "Steph", "717-789-4594", 34), actual);

		}

		// Negative Test
		@Test
		public void testGetClientByIdNotFoundNegative()
				throws SQLException, InvalidParameterException, ClientNotFoundException {

			ClientDAO mockClientDao = mock(ClientDAO.class);

			ClientService clientService = new ClientService(mockClientDao);

			// ACT and ASSERT
			Assertions.assertThrows(ClientNotFoundException.class, () -> {
				clientService.getClientById("1");
			});
		}
		
		// Negative Test
		@Test
		public void testGetClientByIdAlphabeticalIdNegative() {

			ClientDAO mockClientDao = mock(ClientDAO.class);

			ClientService clientService = new ClientService(mockClientDao);

			// ACT and ASSERT
			Assertions.assertThrows(InvalidParameterException.class, () -> {
				clientService.getClientById("abc");
			});
		}

		@Test
		// Negative Test
		public void testClientByIdDecimalIdNegative() throws SQLException, InvalidParameterException, ClientNotFoundException {

			ClientDAO mockClientDao = mock(ClientDAO.class);

			ClientService clientService = new ClientService(mockClientDao);

			// ACT and ASSERT
			Assertions.assertThrows(InvalidParameterException.class, () -> {
				clientService.getClientById("1.0");
			});

		}

}
