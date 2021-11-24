package com.revature.service;

import java.sql.SQLException;

import com.revature.dao.StudentDao;
import com.revature.dto.AddOrUpdateStudentDTO;
import com.revature.exception.StudentNotFoundException;
import com.revature.model.Student;

public class StudentService {
	
	private StudentDao studentDao;
	
	/***
	 * Here StudentService object depends on a 
	 * StudentDao object
	 */
	public StudentService() {
		this.studentDao = new StudentDao();
	}
	
	public Student editFirstNameOfStudent(int studentId, String changedName) throws SQLException, StudentNotFoundException{
		
		//Here we first grab the information about the student with a student id of the value studentId
		Student studentToEdit = this.studentDao.getStudentById(studentId);
		
		if(studentToEdit == null) {
			throw new StudentNotFoundException("Student with an id of " + studentId + " was not found" );
		}
		
		/***
		 * This DTO bellow will contain the first name that will be changed, while
		 * everything else stays the same as before
		 */
		AddOrUpdateStudentDTO dto = new AddOrUpdateStudentDTO(changedName, studentToEdit.getLastName(), studentToEdit.getClassification(),
				studentToEdit.getAge());
		
		Student updateStudent = this.studentDao.updateStudent(studentId, dto);
		System.out.println(updateStudent);
		return updateStudent;
	}
	
	//Inserting new student
	public Student insertNewStudent(String firstName,String lastName, String classification, int age) throws SQLException {
		Student newStudent = new Student();
		
		AddOrUpdateStudentDTO dto = new AddOrUpdateStudentDTO(newStudent.getFirstName(),newStudent.getLastName(),newStudent.getClassification(),
				newStudent.getAge());
		
		Student newAddedStudent = this.studentDao.addStudent(dto);
		
		return newStudent;
	}

	
	
}
