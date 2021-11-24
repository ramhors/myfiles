package com.revature.dto;

import java.util.Objects;

public class AddOrUpdateStudentDTO {
	
	private String firstName;
	private String lastName;
	private String classification;
	private int age;
	
	public  AddOrUpdateStudentDTO() {
	}

	public AddOrUpdateStudentDTO(String firstName, String lastName, String classification, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.classification = classification;
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		return Objects.hash(age, classification, firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddOrUpdateStudentDTO other = (AddOrUpdateStudentDTO) obj;
		return age == other.age && Objects.equals(classification, other.classification)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "AddOrUpdateStudentDTO [firstName=" + firstName + ", lastName=" + lastName + ", classification="
				+ classification + ", age=" + age + "]";
	}
	
	

}
