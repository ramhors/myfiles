package com.revature.model;

import java.util.Objects;

public class Student {
	private int id;
	private String firstName;
	private String classification;
	private int age;
	
	public Student(){}
	
	public Student(int id, String firstName, String classification,int age) {
		this.id = id;
		this.firstName = firstName;
		this.classification = classification;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, classification, firstName, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return age == other.age && Objects.equals(classification, other.classification)
				&& Objects.equals(firstName, other.firstName) && id == other.id;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", classification=" + classification + ", age=" + age
				+ "]";
	}	
	
	
}
