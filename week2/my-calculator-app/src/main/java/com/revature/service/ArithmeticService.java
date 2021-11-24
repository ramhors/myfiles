package com.revature.service;

public class ArithmeticService {
	
	/**********************************************
	 * Checking for input in the field
	 * both field should be filled
	 * 0: FINE, 1: TOP input is missing, 2:BOTTOM input is missing, 3:BOTH inputs are missing
	 * @param input1
	 * @return
	 **********************************************/
	public int checkInputs(String input1, String input2) {
		
		//Check is both inputs fields are empty
		if(input1.trim().equals("") && input2.trim().equals("")) {
			return 3;
		}
		//Checking the top input field
		if(input1.trim().equals("")) {
			return 1;
		}
		//Checking the bottom input field
		if(input2.trim().equals("")) {
			return 2;
		}
		//Neither fields are blank
		return 0;
	}
	
	public String doOperation(String input1, String input2, char operator) {
		int condition = checkInputs(input1,input2);
		
		switch(condition) {
		case 1:
			return "Upper input is missing";
		case 2:
			return "Lower input is missing";
		case 3:
			return "Both input are missing";
					
		}
		//If it is past the switch statements, (for example it gets a value of 0), then it is good.
		switch(operator) {
		case '+':
			return doAddition(input1, input2);
		case '-':
			return doSubtraction(input1,input2);
		case '*':
			return doMultiplication(input1,input2);
		case '/':
			return doDivision(input1,input2);
			
		}
		return "Something went! one of the return statement didn't execute.";
	}
	
		//Setting up the method for the addition
		public String doAddition(String number1String, String number2String) {
			double number1 = Double.parseDouble(number1String);
			double number2 = Double.parseDouble(number2String);

			double sum = number1 + number2;

			String result = "RESULT: " + sum; 
			return result;
			
		}
		//Setting up the method for the subtraction
			public String doSubtraction(String number1String, String number2String) {
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
