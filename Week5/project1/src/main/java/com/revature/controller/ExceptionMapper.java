package com.revature.controller;

import javax.security.auth.login.FailedLoginException;

import com.revature.dto.MessageDTO;
import com.revature.exception.InvalidParameterException;
import com.revature.exception.ReceiptNotFoundException;
import com.revature.exception.ReimbursementAlreadyResolvedException;
import com.revature.exception.ReimbursementNotFound;
import com.revature.exception.UnauthorizedException;

import io.javalin.Javalin;

public class ExceptionMapper {

	public void mapExceptions(Javalin app) {
		app.exception(FailedLoginException.class, (e, ctx) -> {
			ctx.status(400);
			ctx.json(new MessageDTO(e.getMessage()));
			
		});
		
		app.exception(UnauthorizedException.class, (e, ctx) -> {
			ctx.status(401);
			ctx.json(new MessageDTO(e.getMessage()));
		});
		
		app.exception(ReimbursementNotFound.class, (e, ctx) -> {
			ctx.status(404);
			ctx.json(new MessageDTO(e.getMessage()));
		});
		
		app.exception(InvalidParameterException.class, (e, ctx) -> {
			ctx.status(400);
			ctx.json(new MessageDTO(e.getMessage()));
		});
		
		app.exception(ReimbursementAlreadyResolvedException.class, (e, ctx) -> {
			ctx.status(400);
			ctx.json(new MessageDTO(e.getMessage()));
		});
		
		app.exception(ReceiptNotFoundException.class, (e, ctx) -> {
			ctx.status(400);
			ctx.json(new MessageDTO(e.getMessage()));
		});
		
		app.exception(NumberFormatException.class, (e, ctx) -> {
			ctx.status(400);
			ctx.json(new MessageDTO(e.getMessage()));
		});
	}
}
