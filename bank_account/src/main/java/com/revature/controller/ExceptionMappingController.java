package com.revature.controller;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.revature.dto.ExceptionMessageDTO;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.InvalidParameterException;

import io.javalin.Javalin;

public class ExceptionMappingController {

	public void mapExceptions(Javalin app) {
		
		app.exception(UnrecognizedPropertyException.class,(e, ctx) -> {
			ctx.status(400);
			ctx.json(new ExceptionMessageDTO(e));
		});
		
		app.exception(InvalidParameterException.class, (e, ctx) -> {
			ctx.json(new ExceptionMessageDTO(e));
			ctx.status(400);
		
		});
		
		/*******************************
		 * Throwing an exception when we want to modify or delete a client who does not exist
		 */
		app.exception(ClientNotFoundException.class, (e, ctx) -> {
			ctx.json(new ExceptionMessageDTO(e));
			ctx.status(400);
		});
	}
}
