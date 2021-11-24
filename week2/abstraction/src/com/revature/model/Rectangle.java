package com.revature.model;

public class Rectangle extends Shape{
	
	private double length;
	private double width;

	public Rectangle (String description,double length, double width) {
		super(description); //this is the only way to invoke a parent constructor, because you can not instantiate and 
		//abstract class
		this.length = length;
		this.width = width;
	}

	@Override
	public double getArea() {
		return length * width;
	}
}
