package com.revature.model;

public class Family {

	/********************************
	 * Setting up the members of the family and their properties
	 *******************************/
	public int size;

	public Family(int size) {
		super();
		this.size = size;
	}
	
	public void printInfo(int size, int age) {
		System.out.println("Family size is: " + size + " and grandpa is " + age + " years old");
	}
	
		
	

}
