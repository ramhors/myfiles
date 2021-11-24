package com.revature.service;

public class ArithmeticService {

	//Setting the method
		public String doMultiplication(String stringNumber1, String stringNumber2,String stringNumber3) {
			double number1 = Double.parseDouble(stringNumber1);
			double number2 = Double.parseDouble(stringNumber2);
			double number3 = Double.parseDouble(stringNumber3);
			
			double multiplication = (number1 * number2) * number3;
			
			//Convert from double representation of a number to a String representation
			String result = "" + multiplication;
			return result;
		}
}
