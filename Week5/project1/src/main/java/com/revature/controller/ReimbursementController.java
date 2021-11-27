package com.revature.controller;

import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.AuthorizationService;
import com.revature.service.ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementController implements Controller{

	private AuthorizationService authService;
	private ReimbursementService reimbursementService;
	
	public ReimbursementController() {
		this.authService = new AuthorizationService();
		this.reimbursementService = new ReimbursementService();
	}
	
	private Handler getReimbursement = (ctx) -> {
		//Role is permitted for Associate and trainer
		User currentLoggedInUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authService.authorizeRegularAndManager(currentLoggedInUser );
		
		List<Reimbursement> reimbursements = this.reimbursementService.getReimbursement(currentLoggedInUser);
		
		ctx.json(reimbursements);
	};
	
	@Override
	public void mapEndpoints(Javalin app) {
		app.get("/reimbursements", getReimbursement);
		
	}

}
