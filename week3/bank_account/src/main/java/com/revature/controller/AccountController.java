package com.revature.controller;

import java.util.List;

import com.revature.dto.AddOrUpdateAccountDTO;
import com.revature.model.Account;
import com.revature.service.AccountService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AccountController {

	private AccountService accountService;

	public AccountController() {
		this.accountService = new AccountService();
	}

	// UPDATING THE ACCOUNT BALANCE
	private Handler updateAccountBalance = (ctx) -> {

		String a_id = ctx.pathParam("id");
		String c_id = ctx.pathParam("cid");

		int ac_id = Integer.parseInt(a_id);
		int cl_id = Integer.parseInt(c_id);

		AddOrUpdateAccountDTO dto = ctx.bodyAsClass(AddOrUpdateAccountDTO.class);

		Account accountThatWasUpdated = this.accountService.updateAccountBalance(a_id, c_id, dto.getAccountBalance());

		ctx.json(accountThatWasUpdated);
	};

	// Getting all the accounts
	private Handler getAllAccounts = (ctx) -> {
		List<Account> accounts = this.accountService.getAllAccounts();

		ctx.json(accounts);
		//ctx.status(400);
	};

	// Deleting account by Id
	private Handler deleteAccount = (ctx) -> {
		String id = ctx.pathParam("a_id");
		String id2 = ctx.pathParam("c_id");
 
		this.accountService.deleteAccountById(id,id2);
		
		ctx.json("Account with an Id " + id + " is deleted");

	};
	
	//Adding a new account
	private Handler addAccount = (ctx) -> {
		AddOrUpdateAccountDTO dto = ctx.bodyAsClass(AddOrUpdateAccountDTO.class);
		
		Account account = this.accountService.addingAccount(dto);
		
		ctx.json(account);
		ctx.status(404);
	};
	
	//Getting specific account
	private Handler getSpecificAccount = (ctx) -> {
		
		String clientId = ctx.pathParam("id");
		String accountId = ctx.pathParam("id1");
		
		Account account = this.accountService.getAccountByClientId(clientId,accountId);
		
		ctx.json(account);
		ctx.status(404);
		
	};
	
	//Getting account by Client Id
		private Handler getAllAcountByClient = (ctx) -> {
			
			String clientId = ctx.pathParam("id");			
			
			List<Account> account = this.accountService.getAllAccountsForClient(clientId);
			
			ctx.json(account);
			ctx.status(404);
			
		};

	public void registerEndpoints(Javalin app) {
		app.patch("/account/{id}/client/{cid}/accountbalance", updateAccountBalance);
		
		app.delete("/account/{a_id}/clients/{c_id}", deleteAccount);
		
		app.post("/account", addAccount);
		
		app.get("/clients/{id}", getAllAcountByClient);
		app.get("/account", getAllAccounts);
		app.get("/clients/{id}/account/{id1}", getSpecificAccount);
	}

}
