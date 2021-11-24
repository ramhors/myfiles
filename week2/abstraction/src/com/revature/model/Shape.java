package com.revature.model;

public abstract class Shape {

	protected String description;
	
	public Shape(String description) {
		this.description = description;
	}
	
//	public double getArea() {
//		return 0.0;
//	}
	//We use abstract method. There is no code body in abstract method
	public abstract double getArea();
	
	
}
