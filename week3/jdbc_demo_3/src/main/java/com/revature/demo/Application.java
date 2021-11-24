package com.revature.demo;

import java.sql.SQLException;
import java.util.List;

import com.revature.controller.StudentController;
import com.revature.dao.StudentDao;
import com.revature.dto.AddOrUpdateStudentDTO;
import com.revature.exception.StudentNotFoundException;
import com.revature.model.Student;
import com.revature.service.StudentService;

import io.javalin.Javalin;

public class Application {

	public static void main(String[] args)  {
		
		Javalin app = Javalin.create((config) -> {
			
			config.enableCorsForOrigin("config.enableCorsForOrigin(\"http://localhost:5500\", \"http://127.0.0.1:5500\", \"http://student-management-app-1.s3-website.us-east-2.amazonaws.com\"");
			
		});
		
		StudentController controller = new StudentController();
		controller.registrEndpoints(app);
		
		app.start();
	}

}
