package com.revature.model;

import java.util.Objects;

public class Grade {

	private int id;
	private int grade;
	private String assignmentName;
	
	// Constructors
	public Grade() {
	}
	
	public Grade(int id, int grade, String assignmentName) {
		this.id = id;
		this.grade = grade;
		this.assignmentName = assignmentName;
	}

	// Getters/setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	// HashCode, equals, toString
	@Override
	public int hashCode() {
		return Objects.hash(assignmentName, grade, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grade other = (Grade) obj;
		return Objects.equals(assignmentName, other.assignmentName) && grade == other.grade && id == other.id;
	}

	@Override
	public String toString() {
		return "Grade [id=" + id + ", grade=" + grade + ", assignmentName=" + assignmentName + "]";
	}
	
}
