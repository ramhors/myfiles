package com.revature.controller;

import com.revature.service.ArithmeticService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ArithmaticController {
	
	public ArithmeticService arithmaticService;

	// Constructing the Constructor
	public ArithmaticController(ArithmeticService arithmaticService) {
		this.arithmaticService = arithmaticService;
	}

	//Setting up the method for addition
	public Handler add = (ctx) -> {
		ctx.result("add lambda invoked");
		
		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");
		
		ctx.result(arithmaticService.doOperation(number1String, number2String,'+')); 
	};
	
	//Setting up the method for subtraction
	public Handler subtract = (ctx) -> {		
		
		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");

		
		ctx.result(arithmaticService.doOperation(number1String, number2String,'-')); 
	};
	
	//Setting up the method for Division
	public Handler division = (ctx) -> {	
		
		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");

		
		ctx.result(arithmaticService.doOperation(number1String, number2String,'/')); 
	};
	
	//Setting up the method for multiplication
	public Handler multiply = (ctx) -> {		
		
		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");
	
		ctx.result(arithmaticService.doOperation(number1String, number2String,'*')); 
	};
	
	// Define an instance method here
		public void registerEndpoint(Javalin app) {
			//sends an HTTP POST request to "/add"
			app.post("/add", add);
			app.post("/subtract", subtract);
			app.post("/multiply", multiply);
			app.post("/division", division);
			
		}
	
}