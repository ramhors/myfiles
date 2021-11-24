package com.revature.service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dao.GradeDAO;
import com.revature.dao.StudentDAO;
import com.revature.dto.AddOrUpdateStudentDTO;
import com.revature.exceptions.InvalidParameterException;
import com.revature.exceptions.StudentNotFoundException;
import com.revature.model.Grade;
import com.revature.model.Student;

public class StudentService {
	
	private Logger logger = LoggerFactory.getLogger(StudentService.class);
	
	private GradeDAO gradeDao;
	private StudentDAO studentDao; // HAS-A relationship. StudentService HAS-A StudentDAO
	// In other words, StudentService depends on StudentDAO
	// StudentDAO is a dependency of StudentService
	
	// The word dependency has many meanings in programming and technology in general. 
	// 1. Maven dependency: an API of sorts that we are using inside of our own Maven project (Javalin, Postgres Driver, JUnit 5)
	// 2. Object dependencies (ex. a StudentService object depends on a StudentDAO object)
	
	// This constructor up here will create a real StudentDAO object for our StudentService object being constructed
	public StudentService() {
		this.gradeDao = new GradeDAO();
		this.studentDao = new StudentDAO();
	}
	
	// Create a new constructor for us to pass in a mock StudentDAO object
	public StudentService(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}
	
	// Another constructor for passing in mock objects for both StudentDAO and GradeDAO for tests that require both.
	public StudentService(StudentDAO studentDao, GradeDAO gradeDao) {
		this.studentDao = studentDao; 
		this.gradeDao = gradeDao;
	}
	
	/*
	 * This is our service layer logic for updating ONLY the firstName of a student
	 * 
	 * What this method does is first grab the Student with that particular student id from the database
	 * 	- If a student does not exist with that studentId, it will throw a StudentNotFoundException
	 * 	- Otherwise, it will go ahead and call the updateStudent method in the DAO layer and provide the appropriate arguments.
	 * 
	 * 
	 */
	public Student editFirstNameOfStudent(String studentId, String changedName) throws SQLException, StudentNotFoundException, InvalidParameterException {
		
		// Convert the String to an int
		try {
			int id = Integer.parseInt(studentId);
		
			// First, grab the information about the student with a student id of the value studentId
			Student studentToEdit = this.studentDao.getStudentById(id);
		
			// How does getStudentById work?
			// 1st scenario: If we have a studentId that indeed exists in the database, it will return us a Student object containing the information
			// 2nd scenario: If we have a studentId that does not have corresponding record in the database, it will return null
			// null = absence of an object
			if (studentToEdit == null) {
				throw new StudentNotFoundException("Student with an id of " + studentId + " was not found");
			}
		
			// This DTO will contain the first name that will be changed, while everything else stays the same as before
			AddOrUpdateStudentDTO dto = new AddOrUpdateStudentDTO(changedName, studentToEdit.getLastName(), studentToEdit.getClassification(), studentToEdit.getAge());
			// This DTO object contains the firstName, the lastName, the classification, and the age that we want to update the student to
			// Because we are only updating the firstName, that is the only change inside of the DTO. Everything else (lastName, classification, and age) are 
			// populated from the Student object we grabbed from the database (using getStudentById)
			
			Student updatedStudent = this.studentDao.updateStudent(id, dto);
			
			return updatedStudent;
		
		} catch(NumberFormatException e) {
			throw new InvalidParameterException("Id provided is not an int convertable value");
		}
	}
	
	// We know based on our logic, if getAllStudents from the DAO layer throws a SQLException, our service layer will also throw a SQLException
	// Because we are not catching it in this method
	// We are using throws SQLException in the method signature
	public List<Student> getAllStudents() throws SQLException {
		logger.info("getAllStudents() invoked");
		
		List<Student> students = this.studentDao.getAllStudents();
		
		return students;
	}
	
	
	
	public Student getStudentById(String studentId) throws SQLException, InvalidParameterException, StudentNotFoundException {
		// convert from a String to an int
		try {
			int id = Integer.parseInt(studentId);
			
			Student s = this.studentDao.getStudentById(id);
			
			if (s == null) {
				throw new StudentNotFoundException("Student with id of " + studentId + " was not found");
			}
			
			return s;
		} catch(NumberFormatException e) {
			throw new InvalidParameterException("Id provided is not an int convertable value");
		}
		
	}

	/*
	 * business logic
	 * 1. We don't want firstName to be blank
	 * 2. We don't want lastName to be blank
	 * 3. We want classification to be either Freshman, Sophmore, Junior, or Senior
	 * 4. we don't want age to be negative
	 */
	public Student addStudent(AddOrUpdateStudentDTO dto) throws InvalidParameterException, SQLException {
		// dto contains firstName, lastName, classification, age
		if (dto.getFirstName().trim().equals("") || dto.getLastName().trim().equals("")) {
			throw new InvalidParameterException("First name and/or last name cannot be blank");
		}
		
		Set<String> validClassifications = new HashSet<>();
		validClassifications.add("Freshman");
		validClassifications.add("Sophmore");
		validClassifications.add("Junior");
		validClassifications.add("Senior");
		
		// If validClassifications does NOT contain the information provided in the DTO for classification
		if (!validClassifications.contains(dto.getClassification())) {
			throw new InvalidParameterException("You entered an invalid classification");
		}
		
		if (dto.getAge() < 0) {
			throw new InvalidParameterException("Age cannot be less than 0");
		}
		
		// Trim the leading and trailing whitespaces in the first and last names
		dto.setFirstName(dto.getFirstName().trim());
		dto.setLastName(dto.getLastName().trim());
		
		Student insertedStudent = this.studentDao.addStudent(dto);
		
		return insertedStudent;
	}

	/*
	 * 1. Check to see if the studentId provided in the URI is actually an int, and if not, throw an InvalidParameterException
	 * 2. If the student we are trying to delete does not exist, throw a StudentNotFoundException
	 */
	public void deleteStudentById(String studentId) throws InvalidParameterException, SQLException, StudentNotFoundException {
		try {
			int id = Integer.parseInt(studentId); 
			
			// Check to see if a student with that id exists or not
			Student student = this.studentDao.getStudentById(id);
			if (student == null) {
				throw new StudentNotFoundException("Student with id " + id + " was not found, and therefore we cannot delete that student");
			}
			
			// Delete all grades that belong to this student
			// Get all grades that belong to the student
			this.gradeDao.deleteAllGradesByStudentId(id);
			
			// Deleting all the grades that belong to the student then ensures that we can actually delete the student
			this.studentDao.deleteStudentById(id);
			
		} catch(NumberFormatException e) {
			throw new InvalidParameterException("Id supplied is not an int");
		}
	}
	
	
}
