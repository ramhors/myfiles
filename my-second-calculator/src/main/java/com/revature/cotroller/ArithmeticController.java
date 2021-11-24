package com.revature.cotroller;

import com.revature.service.ArithmeticService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ArithmeticController {
	
	public static ArithmeticService arithmeticService;
	
	//Setting up the constructor with one parameter ArithmeticService type
	public ArithmeticController(ArithmeticService arithemticService) {
		this.arithmeticService = arithemticService;
	}
	
	//Construct a lambda method to pass around
	public Handler multiplication = (ctx) -> {
		ctx.result("multiplication lambda invoked");
		
		//double primitive representation of the String
		String stringNumber1 = ctx.formParam("number1");
		String stringNumber2 = ctx.formParam("number2");
		String stringNumber3 = ctx.formParam("number3");
		
		ctx.result(arithmeticService.doMultiplication(stringNumber1, stringNumber2, stringNumber3));		
	};
	
	//Mapping the lambda which will be called whenever a licent sends an HTTP POST request to "multiplication"
	public void registerEndpoint(Javalin app) {
		app.post("/multiplication", multiplication);
	}

}
