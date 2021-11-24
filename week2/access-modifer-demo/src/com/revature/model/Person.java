package com.revature.model;

import java.util.Objects;

public class Person {
	
	public int weight;
	public String firstName;
	protected String lastName;
	double age;
	private String phoneNumber;

	// static fields
	public static int numOfInstane;
	
	//Instance method
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public Person() {
		super();
	}

	public Person(String firstName, String lastName, double age, String phoneNumber,int weight) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.weight = weight;
		this.phoneNumber = phoneNumber;
		numOfInstane++;
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
	public double getAge() {
		return age;
	}
	public void setAge(double age) {
		this.age = age;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getWeight() {
		return this.weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
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
		return Double.doubleToLongBits(age) == Double.doubleToLongBits(other.age)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}
	
	
	
	
}
