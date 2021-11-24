package com.revature.app;



import com.revature.controller.ArithmaticController;
import com.revature.service.ArithmaticService;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Application {
	
	public static void main(String[] args) {

		Javalin app = Javalin.create(config ->{
			config.addStaticFiles("/", Location.CLASSPATH);
		}); 
		

		// Instantiate our Controller
		ArithmaticController controller1 = new ArithmaticController(new ArithmaticService());
		controller1.registerEndpoint(app);

		app.start(8080); // Start the server on port 8080!

	}

}
