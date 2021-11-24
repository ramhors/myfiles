package com.revature.controller;

import com.revature.dto.AddOrUpdateStudentDTO;
import com.revature.service.StudentService;
import com.revature.exception.StudentNotFoundException;
import com.revature.model.Student;

import io.javalin.http.Handler;
import io.javalin.Javalin;

public class StudentController {

	/*******
	 *The Controller layer will be directly communicating with the Service layer, 
	 *and the Service layer will be communicating with the Data access layer
	 *The relationship is: StudentController instance HAS-A StudentService 
	 */
	private StudentService studentService;
	
	public StudentController() {
		this.studentService = new StudentService();
	}
	
	private Handler editStudentFirstName = (ctx) -> {
		
		//Extracting the id from the URL path
		String studentId = ctx.pathParam("id");
		
		int id = Integer.parseInt(studentId);
		
		/***
		 * Extracting the information in the form of JSON from the body and place
		 * it into an object of the type EditFirstNameDTO
		 */
		AddOrUpdateStudentDTO dto = ctx.bodyAsClass(AddOrUpdateStudentDTO.class);
		
		try {
			/**
			 * When we invoke the editFirstNameOfStudent method (service layer), it will then contact the 
			 * updateStudent method in the DAO layer, which then return the Student object of the record
			 * that was newly updated
			 */
			Student studentThatWasJustEdited = this.studentService.editFirstNameOfStudent(id, dto.getFirstName());
			/**
			 * Take the ojbect and convert it into JSON
			 * this JSON is then sent back to the client that sent the request (in our case Postman)
			 */
			ctx.json(studentThatWasJustEdited);
		}catch(StudentNotFoundException e) {
			ctx.status(404);
			ctx.json(e);
		}
	};
	
	//Adding new Student
	public Handler addNewStudent = (ctx) -> {
		
		/***
		 * Extracting the information in the form of JSON from the body and place
		 * it into an object of the type EditFirstNameDTO
		 */
		AddOrUpdateStudentDTO dto = ctx.bodyAsClass(AddOrUpdateStudentDTO.class);
		
		try {
			Student newStudentAdded = this.studentService.insertNewStudent(dto.getFirstName(),dto.getLastName(),dto.getClassification(),
					dto.getAge());
			ctx.json(newStudentAdded);
		}catch(Exception e) {
			System.out.println("No student added");
		}
		
	};
	
	
	public void registrEndpoints(Javalin app) {
		app.patch("/students/{id}/firstname", editStudentFirstName);
	}
	
}
