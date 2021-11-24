package com.revature.controller;

import java.util.List;

import com.revature.dto.AddOrUpdateStudentDTO;
import com.revature.dto.ExceptionMessageDTO;
import com.revature.exceptions.InvalidParameterException;
import com.revature.exceptions.StudentNotFoundException;
import com.revature.model.Student;
import com.revature.service.StudentService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class StudentController {

	// The Controller layer will be directly communicating with the Service layer, and the Service layer will be communicating with the Data access layer
	// Our dependency here for our Controller
	private StudentService studentService; // StudentController instance HAS-A StudentService
	
	public StudentController() {
		this.studentService = new StudentService();
	}
	
	private Handler editStudentFirstName = (ctx) -> {
		
		// Extract the id from the URI path
		String studentId = ctx.pathParam("id");
				
		// Extract the information in the form of JSON from the body and place it into an object of the type EditFirstNameDTO
		AddOrUpdateStudentDTO dto = ctx.bodyAsClass(AddOrUpdateStudentDTO.class); // We need a no-args constructor and setters
		
		// When we invoke the editFirstNameOfStudent method (service layer), it will then contact the updateStudent method in the DAO layer, which
		// will then return the Student object representation of the record that was newly updated
		Student studentThatWasJustEdited = this.studentService.editFirstNameOfStudent(studentId, dto.getFirstName());
		
		// We then take that object and convert it into JSON
		// This JSON is then sent back to the client that sent the request (in our case, Postman)
		ctx.json(studentThatWasJustEdited); // For this to work, we need to have getter methods that exist for that object
		
	};
	
	private Handler addStudent = (ctx) -> {
		AddOrUpdateStudentDTO dto = ctx.bodyAsClass(AddOrUpdateStudentDTO.class);
		
		Student s = this.studentService.addStudent(dto);
		
		ctx.json(s);
		ctx.status(201); // 201 CREATED
	};
	
	private Handler getAllStudents = (ctx) -> {
		List<Student> students = this.studentService.getAllStudents();
		
		ctx.json(students);
	};
	
	private Handler getStudentById = (ctx) -> {
		String id = ctx.pathParam("id");
		
		Student s = this.studentService.getStudentById(id);
			
		ctx.json(s);
	};
	
	private Handler editStudentById = (ctx) -> {
		
	};
	
	private Handler deleteStudentById = (ctx) -> {
		String id = ctx.pathParam("id");
		
		this.studentService.deleteStudentById(id);
	};
	
	private Handler deleteAllStudents = (ctx) -> {
		
	};
	
	/*
	 * HTTP: HyperText Transfer Protocol
	 * 
	 * Widely used protocol for communicating over the internet.
	 * 
	 * It is composed of requests and responses. A client will send a request to a server, and the server will then send a response to the client.
	 * 
	 * Example of an HTTP Client: Postman, Chrome, Internet Explorer, Safari, Firefox
	 * Example of an HTTP Server: Our Javalin application
	 * 
	 * ==================================================================================================
	 * 
	 * HTTP Requests:
	 * 	1. An HTTP method
	 * 		- GET: get a particular resource (ex. GET /students <- get a list of all students)
	 * 		- POST: add a particular resource (ex. POST /students <- add a student)
	 * 		- PUT: replace an entire resource (ex. PUT /students/1 <- replace an entire student w/ id of 1 with an entirely new set of properties)
	 * 		- PATCH: partially replace a resource (ex. PATCH /students/1/firstname <- replace the firstName of student w/ id 1 to something else)
	 * 		- DELETE: delete a resource (ex. DELETE /students/1 <- delete a student w/ id 1)
	 * 
	 * 	HTTP methods do not implement anything on their own. It is just part of a convention that this protocol uses when dealing with resources.
	 * 	It is up to the programmer to use the correct conventions and implement the functionality of their HTTP backend to support these operations
	 *  with different resources. HTTP methods are often also known as HTTP verbs. This naming is made because it is of course, similar to grammar. 
	 *  It is up to the person speaking on whether they are using the grammar correctly or not.
	 * 
	 * 	2. URI (uniform resource identifier)
	 * 	3. Request headers
	 * 	4. Request body
	 * 
	 * Ex. PATCH /students/7/firstname
	 * 		-> Request body: { "firstName" : "asdfdsfkj" }
	 * 
	 * The server will receive the request, and then provide an HTTP Response
	 * 
	 * ==================================================================================================
	 * 
	 * HTTP Responses:
	 * 	1. Status Code: 1XX means informational, 2XX means OK, 3XX for redirects, 4XX client error, 5XX Server sided error 
	 * 		(5XX usually means you didn't catch an exception and then provide a more specific response status code)
	 *  2. Response Headers
	 *  3. Response Body
	 *  // Ex. => { "id": 1, "firstName": "Trevor", "lastName": "Doe", "classification": "Freshman", "age": 18 }
	 */
	
	
	public void registerEndpoints(Javalin app) {
		app.patch("/students/{id}/firstname", editStudentFirstName);
		
		app.post("/students", addStudent);
		app.get("/students", getAllStudents);
		app.get("/students/{id}", getStudentById);
		app.put("/students/{id}", editStudentById);
		app.delete("/students/{id}", deleteStudentById);
		app.delete("/students", deleteAllStudents);
	}
	
}
