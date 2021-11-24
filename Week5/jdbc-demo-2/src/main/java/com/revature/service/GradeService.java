package com.revature.service;

import java.sql.SQLException;
import java.util.List;

import com.revature.dao.GradeDAO;
import com.revature.dao.StudentDAO;
import com.revature.exceptions.InvalidParameterException;
import com.revature.exceptions.StudentNotFoundException;
import com.revature.model.Grade;

import io.javalin.http.Context;

public class GradeService {

	private GradeDAO gradeDao;
	private StudentDAO studentDao;
	
	public GradeService() {
		this.gradeDao = new GradeDAO();
		this.studentDao = new StudentDAO();
	}
	
	// For mock objects (Mockito)
	public GradeService(GradeDAO gradeDao, StudentDAO studentDao) {
		this.gradeDao = gradeDao;
		this.studentDao = studentDao;
	}
	
	// Business logic we want to make sure that this student actually exists
	// 	If the student does not exist, throw a StudentNotFoundException
	public List<Grade> getAllGradesByStudentId(String studentId, Context ctx) throws InvalidParameterException, StudentNotFoundException, SQLException {
		
		List<Grade> grades;
		
		int id = Integer.parseInt(studentId);
		
		if (ctx.queryParam("greaterThan") != null && ctx.queryParam("lessThan") != null) { // Using both
			int greaterThan = Integer.parseInt(ctx.queryParam("greaterThan"));
			int lessThan = Integer.parseInt(ctx.queryParam("lessThan"));
			
			grades = this.gradeDao.getAllGradesByStudentId(id, greaterThan, lessThan);
		} else if (ctx.queryParam("lessThan") != null) { // using only lessThan
			
			int lessThan = Integer.parseInt(ctx.queryParam("lessThan"));
					
			grades = this.gradeDao.getAllGradesByStudentId(id, 0, lessThan);
		} else if (ctx.queryParam("greaterThan") != null) { // using only greaterThan
			
			int greaterThan = Integer.parseInt(ctx.queryParam("greaterThan"));
			
			grades = this.gradeDao.getAllGradesByStudentId(id, greaterThan, 100);
			
		} else {
			grades = this.gradeDao.getAllGradesByStudentId(id, 0, 100);
		}
		
		return grades;
	}
	
}
