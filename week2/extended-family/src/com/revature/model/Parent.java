package com.revature.model;

public class Parent {

	public String firstName;
	public String lastName;
	
	
	
	public Parent(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void printInfo(String firstName, String lastName) {
		System.out.println("Parent First Name: " + firstName + "\n\t Last Name: " + lastName);
	}
	
	
}
