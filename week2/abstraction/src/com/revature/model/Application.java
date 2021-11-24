package com.revature.model;

public class Application {

	public static void main(String[] args) {
		
		Shape a;
		
		
		a = new Rectangle("this rectangle is modeling the top of my desk" , 20 ,50);
		System.out.println("Rectangle: " + a.getArea());
		
		a = new Circle("This circle is modelling my beverage coaster",2.5);
		System.out.println("Circle: " + a.getArea());
		
		a = new Triangle("This is modelling my pizza slice",8,20);
		System.out.println("Triangle: " + a.getArea());
		
	}

}
