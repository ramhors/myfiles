package com.revature.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controller.ExceptionMappingController;
import com.revature.controller.GradeController;
import com.revature.controller.StudentController;

import io.javalin.Javalin;

public class Application {

	public static void main(String[] args) {
				
		// Create the Javalin object and also configure CORS settings
		Javalin app = Javalin.create((config) -> {
			// CORS is a security setting in modern web browsers such as Chrome
			// That prevent arbitrary HTTP requests to be sent by potentially malicious JS code
			// The backend needs to be configured to tell the browser what sources are "trusted"
			// IN essence, because the JS code is being served from http://localhost:5500 (or http://127.0.0.1:5500), we need to configure
			// on the backend that those are trusted sources.
			// The backend will then tell the browser whenever the browser makes a request, that it is ok to do so, because those sources are trusted
			config.enableCorsForOrigin("http://localhost:5500", "http://127.0.0.1:5500", "http://student-management-app-1.s3-website.us-east-2.amazonaws.com");
		});
		
		Logger logger = LoggerFactory.getLogger(Application.class);
		
		app.before(ctx -> {
			logger.info(ctx.method() + " request received to the " + ctx.path() + " endpoint");
		});
		
		StudentController controller = new StudentController();
		controller.registerEndpoints(app);
		
		ExceptionMappingController exceptionController = new ExceptionMappingController();
		exceptionController.mapExceptions(app);
		
		GradeController gradeController = new GradeController();
		gradeController.registerEndpoints(app);
				
		app.start();
		
	}

}
