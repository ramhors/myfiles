package com.revature.model;

public class GrandParent extends Family{
	
	/*************************************
	 *Setting up the properties or 
	 *attributes the GrandParent 
	 ************************************/
	
	public int age;	
	
	public GrandParent(int size, int age) {
		super(size);
		this.age = age;
	}

	public void disaplyInfo() {
		System.out.println("Grandpa is still strong");
	}
	
	
}
