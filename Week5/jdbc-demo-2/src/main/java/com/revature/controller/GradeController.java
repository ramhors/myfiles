package com.revature.controller;

import java.util.List;

import com.revature.model.Grade;
import com.revature.service.GradeService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class GradeController {

	private GradeService gradeService;

	public GradeController() {
		this.gradeService = new GradeService();
	}

	private Handler getAllGradesByStudentId = (ctx) -> {
		String studentId = ctx.pathParam("id");

		// Using query parameters, you can have 4 situations:
		// 1. No lessThan or greaterThan at all
		// 2. Using ONLY lessThan
		// 3. Using ONLY greaterThan
		// 4. Using BOTH
		
		// We are dealing with these 4 situations in our service layer by passing in the ctx object, so that our service layer
		// can examine the query parameters
		List<Grade> grades = this.gradeService.getAllGradesByStudentId(studentId, ctx);

		ctx.json(grades);
	};

	public void registerEndpoints(Javalin app) {
		app.get("/students/{id}/grades", getAllGradesByStudentId);
	}

}
