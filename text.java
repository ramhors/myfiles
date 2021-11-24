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
	//Positive test for editing firstname
	@Test
	public void testEditFirstNameOfClientPositive() throws SQLException, InvalidParameterException, ClientNotFoundException{
		
		ClientDAO mockClientDao = mock(ClientDAO.class);
		
		when(mockClientDao.getClientById(eq(5))).thenReturn(new Client(5, "Patrcik", "Smith", "717-451-7852", 45));
		
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Gregory", "Smith", "717-451-7852", 45);
		
		when(mockClientDao.updateClient(eq(5), eq(dto))).thenReturn(new Client(5, "Gregory", "Smith", "717-451-7852", 45));
		
		ClientService clientService = new ClientService(mockClientDao);
		
		//ACT
		
		Client actual = clientService.editFirstNameOfClient("5", "Gregory");
		
		//ASSERT
		Client expected = new Client(5,"Gregory", "Smith", "717-451-7852", 45);
		
		
		Assertions.assertEquals(expected, actual);
	}
	
	//
	
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
	
	
	//NEGATIVE TEST FOR EDIT FIRSTNAME
	@Test
	public void testEditFirstNameOfClientButClientWithId5DoesNotExist() {
		
		ClientDAO mockClientDao = mock(ClientDAO.class);
		
		ClientService clientService = new ClientService(mockClientDao);
		
		//ACT and ASSERT
		
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
	//Positive test for Adding client
	
	@Test
	public void testAddClientAllInformationCorrectInDTO() throws SQLException, InvalidParameterException, ClientNotFoundException {
		
		ClientDAO clientDao = mock(ClientDAO.class);
		
		AddOrUpdateClientDTO dtoIntoDao = new AddOrUpdateClientDTO("Steve","Stephen","717-852-1542",34);
		
		when(clientDao.addClient(eq(dtoIntoDao))).thenReturn(new Client(5,"Steve","Stephen","717-852-1542",34));
		
		ClientService clientService = new ClientService(clientDao);
		
		//ACT
		AddOrUpdateClientDTO  dto = new AddOrUpdateClientDTO("Steve","Stephen","717-852-1542",34);
		Client actual = clientService.addClient(dto);
		
		//ASSERT
		Client expected = new Client(5,"Steve","Stephen","717-852-1542",34);
		Assertions.assertEquals(expected, actual);
		
	}
	/*********
	 * NEGATIVE test
	 * Scenario: Everything is correct except the firstName was left blank
	 */
	@Test
	public void testAddClientFirstNameBlankEverythingElseValid() throws SQLException, InvalidParameterException, ClientNotFoundException {
		
		ClientDAO clientDao = mock(ClientDAO.class);
		
		ClientService clientService = new ClientService(clientDao);
		
		//ACT AD ASSERT
		AddOrUpdateClientDTO  dto = new AddOrUpdateClientDTO(" ","Stephen","717-852-1542",34);
		
		Assertions.assertThrows(InvalidParameterException.class, () -> {
			
			clientService.addClient(dto);
			
		});
		
	}
	/***************
	 * NEGATIVE TEST
	 * Scenario: Everything is correct except the lastName was left blank
	 */
	@Test
	public void testAddStudentLasstNameBlankEverythingElseValid() throws SQLException, InvalidParameterException, ClientNotFoundException {
		
		ClientDAO clientDao = mock(ClientDAO.class);
		
		ClientService clientService = new ClientService(clientDao);
		
		//ACT AD ASSERT
		AddOrUpdateClientDTO  dto = new AddOrUpdateClientDTO("Steve","   ","717-852-1542",34);
		
		Assertions.assertThrows(InvalidParameterException.class, () -> {
			
			clientService.addClient(dto);
			
		});
	}
	
	/***************
	 * NEGATIVE TEST
	 * Scenario: Everything is correct except the phone Number was left blank
	 */
	@Test
	public void testAddStudentPhoneNumberBlankEverythingElseValid() throws SQLException, InvalidParameterException, ClientNotFoundException {
		
		ClientDAO clientDao = mock(ClientDAO.class);
		
		ClientService clientService = new ClientService(clientDao);
		
		//ACT AD ASSERT
		AddOrUpdateClientDTO  dto = new AddOrUpdateClientDTO("Steve","Bob","     ",34);
		
		Assertions.assertThrows(InvalidParameterException.class, () -> {
			
			clientService.addClient(dto);
			
		});
	}
	
	/***************
	 * NEGATIVE TEST
	 * Scenario: Everything is correct except the Age is negative
	 */
	@Test
	public void testAddStudentAgeBlankEverythingElseValid() throws SQLException, InvalidParameterException, ClientNotFoundException {
		
		ClientDAO clientDao = mock(ClientDAO.class);
		
		ClientService clientService = new ClientService(clientDao);
		
		//ACT AD ASSERT
		AddOrUpdateClientDTO  dto = new AddOrUpdateClientDTO("Steve","Bob","717-245-5698", -12 );
		
		Assertions.assertThrows(InvalidParameterException.class, () -> {
			
			clientService.addClient(dto);
			
		});
	}
	
	/***************
	 * NEGATIVE TEST
	 * Scenario: Everything is correct except both firstName and last name left blank
	 */
	@Test
	public void testAddStudentFirstNameAndLastNameBlankEverythingElseValid() throws SQLException, InvalidParameterException, ClientNotFoundException {
		
		ClientDAO clientDao = mock(ClientDAO.class);
		
		ClientService clientService = new ClientService(clientDao);
		
		//ACT AD ASSERT
		AddOrUpdateClientDTO  dto = new AddOrUpdateClientDTO("   ","   ","717-245-5698",0 );
		
		Assertions.assertThrows(InvalidParameterException.class, () -> {
			
			clientService.addClient(dto);
			
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
	
	//Positive test for editing LastName
	@Test
	public void testEditLastNameOfClientPositive() throws SQLException, InvalidParameterException, ClientNotFoundException {
		
		ClientDAO mockClientDao = mock(ClientDAO.class);
		
		when(mockClientDao.getClientById(eq(11))).thenReturn(new Client(11, "Steve", "Klem", "800-458-4512", 42));
		
		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Steve", "Bob", "800-458-4512", 42);
		
		when(mockClientDao.updateClient(eq(11), eq(dto))).thenReturn(new Client(11, "Steve", "Bob", "800-458-4512", 42));
		
		ClientService clientService = new ClientService(mockClientDao);
		
		//ACT
		Client actual = clientService.editLastNameOfClient("11", "Bob");
		
		//ASSERT
		Client expected = new Client(11, "Steve", "Bob", "800-458-4512", 42);
		
		Assertions.assertEquals(expected, actual);
		
	} 