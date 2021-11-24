package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revature.dto.LoginDTO;
import com.revature.model.User;
import com.revature.service.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AuthenticationController implements Controller{
	
	private UserService userService;
	
	public AuthenticationController() {
		this.userService = new UserService();
	}

	private Handler login = (ctx) -> {
		LoginDTO loginDTO = ctx.bodyAsClass(LoginDTO.class);
		User user = this.userService.getUserByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
		
		HttpServletRequest req = ctx.req;				
		
			HttpSession session = req.getSession();
			session.setAttribute("currentuser", user);
			ctx.json(user);
	};		
	
	
	private Handler logout = (ctx) -> {
		HttpServletRequest req = ctx.req;
		
		req.getSession().invalidate();
		ctx.json("You are logged out");
	};
	
	
	@Override
	public void mapEndpoints(Javalin app) {
		app.post("/login", login);
		app.post("/logout", logout);		
		
	}

}
