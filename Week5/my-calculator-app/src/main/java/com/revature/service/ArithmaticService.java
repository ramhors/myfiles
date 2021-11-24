package com.revature.service;

public class ArithmaticService {
	
	
		//Setting up the method for the addition
		public String doAddition(String number1String, String number2String) {
			double number1 = Double.parseDouble(number1String);
			double number2 = Double.parseDouble(number2String);

			double sum = number1 + number2;

			String result = "RESULT: " + sum; 
			return result;
			
		}
		//Setting up the method for the subtraction
			public String doSubstraction(String number1String, String number2String) {
				double number1 = Double.parseDouble(number1String);
				double number2 = Double.parseDouble(number2String);

				double sub = Math.ceil(number1 - number2);

				String result = "RESULT: " + sub; 	
				return result;
		}
		//Setting up the method for multiplication	
			public String doMultiplication(String number1String, String number2String) {
				double number1 = Double.parseDouble(number1String);
				double number2 = Double.parseDouble(number2String);

				double multiplication = number1 * number2;

				String result = "RESUT: " + multiplication;
   			    return result;
		}
		//Setting up the method for Division
			public String doDivision(String number1String, String number2String) {
				double number1 = Double.parseDouble(number1String);
				double number2 = Double.parseDouble(number2String);

				double division =  Math.ceil(number1 / number2);
				String result = "RESULT: " + division; 	 
			    return result;
		}

}
