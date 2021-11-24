package com.revature.service;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.revature.dao.AccountDAO;
import com.revature.dao.ClientDAO;
import com.revature.dto.AddOrUpdateAccountDTO;
import com.revature.exception.AccountNotFoundException;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.InvalidParameterException;
import com.revature.model.Account;

public class AccountServiceTest {

	private AccountService sut;

	private ClientService clientService;

	// Positive test for getting all accounts

	@Test
	public void testGetAllAccountsPositive() throws SQLException, AccountNotFoundException, InvalidParameterException {

		ClientDAO clientDao = new ClientDAO();
		AccountDAO mockAccountDao = mock(AccountDAO.class);

		Account account1 = new Account(7, "Checking", 456.12, "456-4587", 4, "Active");
		Account account2 = new Account(8, "Saving", 4587.12, "487-4587", 4, "Active");
		Account account3 = new Account(10, "Checking", 456.12, "124-4587", 5, "Active");
		Account account4 = new Account(11, "Saving", 5648.45, "456-7814", 5, "Active");
		Account account5 = new Account(12, "Checking", 456.12, "171-4587", 6, "Active");
		Account account6 = new Account(13, "Saving", 10456.12, "456-964", 6, "Active");

		List<Account> accountFromDao = new ArrayList<>();
		accountFromDao.add(account1);
		accountFromDao.add(account2);
		accountFromDao.add(account3);
		accountFromDao.add(account4);
		accountFromDao.add(account5);
		accountFromDao.add(account6);

		when(mockAccountDao.getAllAccounts()).thenReturn(accountFromDao);

		AccountService accountService = new AccountService(mockAccountDao, clientDao);

		// ACT
		List<Account> actual = accountService.getAllAccounts();
		// ASSERT
		List<Account> expected = new ArrayList();
		expected.add(new Account(7, "Checking", 456.12, "456-4587", 4, "Active"));
		expected.add(new Account(8, "Saving", 4587.12, "487-4587", 4, "Active"));
		expected.add(new Account(10, "Checking", 456.12, "124-4587", 5, "Active"));
		expected.add(new Account(11, "Saving", 5648.45, "456-7814", 5, "Active"));
		expected.add(new Account(12, "Checking", 456.12, "171-4587", 6, "Active"));
		expected.add(new Account(13, "Saving", 10456.12, "456-964", 6, "Active"));

		Assertions.assertEquals(expected, actual);
	}

	// Negative test for get all accounts
	@Test
	public void testGetAllAccountsSQLExceptionOccursNegative()
			throws SQLException, AccountNotFoundException, InvalidParameterException {

		ClientDAO clientDao = new ClientDAO();

		AccountDAO mockAccountDao = mock(AccountDAO.class);

		when(mockAccountDao.getAllAccounts()).thenThrow(SQLException.class);

		AccountService accountService = new AccountService(mockAccountDao, clientDao);

		Assertions.assertThrows(SQLException.class, () -> {

			accountService.getAllAccounts();
			accountService.getAllAccounts();
		});
	}

	// Positive Test for adding account
	@Test
	public void testAddAccountAllInformationInDTO()
			throws SQLException, AccountNotFoundException, InvalidParameterException {

		ClientDAO clientDao = new ClientDAO();

		AccountDAO accountDao = mock(AccountDAO.class);

		AddOrUpdateAccountDTO dtoIntoDao = new AddOrUpdateAccountDTO("Checking", 456.12, "458-7845", 4, "Active");

		when(accountDao.addAccount(eq(dtoIntoDao)))
				.thenReturn(new Account(5, "Checking", 456.12, "458-7845", 4, "Active"));

		AccountService accountService = new AccountService(accountDao, clientDao);

		// ACT
		AddOrUpdateAccountDTO dto = new AddOrUpdateAccountDTO("Checking", 456.12, "458-7845", 4, "Active");
		Account actual = accountService.addingAccount(dto);

		// ASSERT
		Account expected = new Account(5, "Checking", 456.12, "458-7845", 4, "Active");
		Assertions.assertEquals(expected, actual);

	}

	/**********************
	 * NEGATIVE TEST Everything is correct except the account type is left blank
	 */
	@Test
	public void testAddAccountTheAccountTypeBlankTheRestAreValid()
			throws SQLException, AccountNotFoundException, InvalidParameterException {

		ClientDAO clientDao = new ClientDAO();

		AccountDAO accountDao = mock(AccountDAO.class);

		AccountService accountService = new AccountService(accountDao, clientDao);

		// ACT ASSERT
		AddOrUpdateAccountDTO dto = new AddOrUpdateAccountDTO("    ", 456.12, "458-7845", 4, "Active");

		Assertions.assertThrows(InvalidParameterException.class, () -> {

			accountService.addingAccount(dto);

		});
	}

	@Test
	/*********************************
	 * NEGATIVE TEST Everything is correct except the account balance was Negative
	 */

	public void testAddAccountTheAccountBalanceIsNegativeTheRestAreValid()
			throws SQLException, AccountNotFoundException, InvalidParameterException {

		ClientDAO clientDao = new ClientDAO();

		AccountDAO accountDao = mock(AccountDAO.class);

		AccountService accountService = new AccountService(accountDao, clientDao);

		// ACT ASSERT
		AddOrUpdateAccountDTO dto = new AddOrUpdateAccountDTO("Checking", -45.12, "458-7845", 4, "Active");

		Assertions.assertThrows(InvalidParameterException.class, () -> {

			accountService.addingAccount(dto);

		});
	}

	@Test
	/*********************************
	 * NEGATIVE TEST Everything is correct except the account balance was blank
	 */
	public void testAddAccountThePhoneNumberIsBlankTheRestAreValid()
			throws SQLException, AccountNotFoundException, InvalidParameterException {

		ClientDAO clientDao = new ClientDAO();

		AccountDAO accountDao = mock(AccountDAO.class);

		AccountService accountService = new AccountService(accountDao, clientDao);

		// ACT ASSERT
		AddOrUpdateAccountDTO dto = new AddOrUpdateAccountDTO("Checking", -45.12, " ", 4, "Active");

		Assertions.assertThrows(InvalidParameterException.class, () -> {

			accountService.addingAccount(dto);

		});
	}

	@Test
	/*********************************
	 * NEGATIVE TEST Everything is correct except the clientId is negative
	 */
	public void testAddAccountTheClientIdIsNegativeTheRestAreValid()
			throws SQLException, AccountNotFoundException, InvalidParameterException {

		ClientDAO clientDao = new ClientDAO();

		AccountDAO accountDao = mock(AccountDAO.class);

		AccountService accountService = new AccountService(accountDao, clientDao);

		// ACT ASSERT
		AddOrUpdateAccountDTO dto = new AddOrUpdateAccountDTO("Checking", -45.12, "717-485-4586", -4, "Active");

		Assertions.assertThrows(InvalidParameterException.class, () -> {

			accountService.addingAccount(dto);

		});
	}

	@Test
	/*********************************
	 * NEGATIVE TEST Everything is correct except the status was blank
	 */
	public void testAddAccountStatusIsBlankOrInvalidTheRestAreValid()
			throws SQLException, AccountNotFoundException, InvalidParameterException {

		ClientDAO clientDao = new ClientDAO();

		AccountDAO accountDao = mock(AccountDAO.class);

		AccountService accountService = new AccountService(accountDao, clientDao);

		// ACT ASSERT
		AddOrUpdateAccountDTO dto = new AddOrUpdateAccountDTO("Checking", -45.12, "717-485-4586", -4, " sdfsdf  ");

		Assertions.assertThrows(InvalidParameterException.class, () -> {

			accountService.addingAccount(dto);

		});
	}

	@Test
	/*********************************
	 * NEGATIVE TEST Everything is correct except the accoutType and PhoneNumber is
	 * blank
	 */
	public void testAddAccountTheAccountTypeandPhoneNumberAreBlankTheRestAreValid()
			throws SQLException, AccountNotFoundException, InvalidParameterException {

		ClientDAO clientDao = new ClientDAO();

		AccountDAO accountDao = mock(AccountDAO.class);

		AccountService accountService = new AccountService(accountDao, clientDao);

		// ACT ASSERT
		AddOrUpdateAccountDTO dto = new AddOrUpdateAccountDTO("   ", -45.12, "  ", -4, "Active");

		Assertions.assertThrows(InvalidParameterException.class, () -> {

			accountService.addingAccount(dto);

		});
	}

	@Test
	/*********************************
	 * NEGATIVE TEST Everything is correct except the acountType and status are blank
	 */
	public void testAddAccountTheAccountTypeAndStatusAreBlankTheRestAreValid()
			throws SQLException, AccountNotFoundException, InvalidParameterException {

		ClientDAO clientDao = new ClientDAO();

		AccountDAO accountDao = mock(AccountDAO.class);

		AccountService accountService = new AccountService(accountDao, clientDao);

		// ACT ASSERT
		AddOrUpdateAccountDTO dto = new AddOrUpdateAccountDTO("  ", -45.12, "717-154-4589 ", -4, "  ");

		Assertions.assertThrows(InvalidParameterException.class, () -> {

			accountService.addingAccount(dto);

		});
	}
	
	/********
	 * Negative text with Add acount,most field left blank, account balance negative and only client id filled
	 * @throws SQLException
	 * @throws AccountNotFoundException
	 * @throws InvalidParameterException
	 */
	@Test
	public void testAddAccountButOnlyClientIdWasFilledTheRestLeftBlank() throws SQLException, AccountNotFoundException, InvalidParameterException {
		
		ClientDAO clientDao = new ClientDAO();

		AccountDAO accountDao = mock(AccountDAO.class);

		AccountService accountService = new AccountService(accountDao, clientDao);

		// ACT ASSERT
		AddOrUpdateAccountDTO dto = new AddOrUpdateAccountDTO("  ", -0, " ", 12, "  ");

		Assertions.assertThrows(InvalidParameterException.class, () -> {

			accountService.addingAccount(dto);

		});
	}

}
