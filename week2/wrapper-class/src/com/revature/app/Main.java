package com.revature.app;

public class Main {
	
	public static void main(String[] args) {
		//Wrapper class
		
		Integer myInt = 100;
		Double weight = 5.67;
		Character myChar = 'B';
		int age = 10;
		
		
		System.out.println(myInt);
		System.out.println(weight);
		System.out.println(myChar);
		//OR use the method
		
		System.out.println(myInt.intValue());
		System.out.println(weight.doubleValue());
		System.out.println(myChar.charValue());
		
		//Use toString() method to convert wrapper objects to strings
		String myString = myInt.toString();
		System.out.println("Length of the sting: " + myString.length());
		
		//Error handling
		try {
			int[] myNumbers = {1,4,6,7};
			System.out.println(myNumbers[4]);
			
		}catch (Exception e) {
			System.out.println("Something went wrong in the code");
		}finally {
			System.out.println("The tray catch is done");
		}
		
		//Using Throw keyword
		if(age < 18) {
			throw new ArithmeticException("Access denied! you are too young to watch this movie");
		}
		else {
			System.out.println("Access granted");
		}
		
		//List
		List<Type>
		
		
	}

}
