package com.revature.app;

import com.revature.controller.AccountController;
import com.revature.controller.ClientController;
import com.revature.controller.ExceptionMappingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.javalin.Javalin;

public class Application {

	public static void main(String[] args) {
		
		Javalin app = Javalin.create((config) -> {
			
			config.enableCorsForOrigin("http://localhost:5500", "http://127.0.0.1:5500", "http://student-management-app-1.s3-website.us-east-2.amazonaws.com");
		});
		
		Logger logger = LoggerFactory.getLogger(Application.class);
		
		app.before(ctx -> {
			logger.info(ctx.method() + "request received to the " + ctx.path() + " endpoint");
		});
		
		ClientController controller = new ClientController();
		controller.registerEndpoints(app);
		
		AccountController accountController = new AccountController();
		accountController.registerEndpoints(app);
		
		ExceptionMappingController exceptionController = new ExceptionMappingController();
		exceptionController.mapExceptions(app);
		
		app.start();
	}

}
