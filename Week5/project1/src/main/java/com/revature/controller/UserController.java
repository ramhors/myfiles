package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revature.dto.AddOrUpdateUsersDTO;
import com.revature.model.User;
import com.revature.service.AuthorizationService;
import com.revature.service.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserController implements Controller {

	private AuthorizationService authorizationService;

	private UserService userService;
	
	

	public UserController() {
		this.authorizationService = new AuthorizationService();
		this.userService = new UserService();
	}

	/***
	 * This is a protected endpoint that can only be accessed when long as either
	 * regular or admin
	 */
	private Handler getUserById = (ctx) -> {
		User user = (User) ctx.req.getSession().getAttribute("currentuser");
		// Authorizing either admin or regular
		this.authorizationService.authorizeRegularAndManager(user);

		String id = ctx.pathParam("userId");

		// TO DO
	};

	// Protected endpoint that can only be accessed when logged in as admin
	private Handler addUser = (ctx) -> {
		User currentlyLoggedInUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.authorizationService.authorizeManager(currentlyLoggedInUser);

		AddOrUpdateUsersDTO dto = ctx.bodyAsClass(AddOrUpdateUsersDTO.class);

		if (currentlyLoggedInUser.getUserRole().equals("manager")) {
			User addedUser = this.userService.addUser(dto);
			ctx.json(addedUser);
			ctx.status(201);
		}

	};

	@Override
	public void mapEndpoints(Javalin app) {
		app.post("/user", addUser);
		app.get("/user/{userId}", getUserById);

	}
}
