package com.revature.model;

import java.util.Objects;

//We want to wrap up data about a person into a single unit

public class Person {

	private String firstName;
	private String lastName;
	private int age;
	private String phoneNumber;
	
	//No arg constructor Java Bean
	public Person() {
		super();
	}
	
	public Person(String firstName, String lastName, int age, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.phoneNumber =phoneNumber;
	}
	//Setting up getter 
	public String getFirstName() {
		return firstName;
	}
	//Setting up the setter
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	//Setting up getter
	public String getLastName() {
		return lastName;
	}
	//Setting up the setter
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	//Setting up getter
	public int getAge() {
		return age;
	}
	//Setting up the setter
	public void setAge(int age) {
		this.age = age;
	}
	//Setting up getter
	public String getPhoneNumber() {
		return phoneNumber;
	}
	//Setting up the setter
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	//Object class overrid

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", phoneNumber="
				+ phoneNumber + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, firstName, lastName, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return age == other.age && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(phoneNumber, other.phoneNumber);
	}
	
	
}
