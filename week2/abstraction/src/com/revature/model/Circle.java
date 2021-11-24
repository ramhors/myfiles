package com.revature.model;

public class Circle extends Shape{
	private double radius;

	public Circle(String description, double radius) {
		super(description);
		
		this.radius = radius;
	}
		@Override
		public double getArea() {
			return radius * radius * Math.PI;
	}
		
}

