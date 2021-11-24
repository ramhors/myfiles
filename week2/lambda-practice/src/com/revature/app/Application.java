package com.revature.app;

import java.util.*;

interface MathOperation{
	double add(double a, double b);
}

public class Application {
			
	public static void main(String[] args) {
		
		Application tester = new Application();
		
		/********************************
		 * Practice on lambda expression
		 *
		 ******************************/
		//with type declaration
		MathOperation addition = (a,b) -> a + b;
		
		//without type declaration		
		MathOperation subtraction = (a,b) -> a - b;
		
		//with return statement and without curly braces
		MathOperation multiplication = (a,b) -> {return a * b;};
		
		//without return statement and without curly braces
		MathOperation division = (a,b) -> a / b;
		
		System.out.println("Result: " + tester.operate(10, 5,addition));
	}

}
