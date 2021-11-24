package com.revature.app;

public class Driver {
	
	public static void main(String[] args) {
		
		String s1 = "Hello World";
		
		char[] myCharacters = {'H', 'e', 'l','l','o', ' ', 'W','o','r','l','d'};
		String s2 = new String(myCharacters);
		
		//System.out.println("String s1 " + s1 + "\nString s2 " + s2);
		
		s1.replace("l", "m");
		
		//String split
		String s4 = "Helo, world, my name, is, back";
		String[] myString = s4.split(", ");
		
		for(int i = 0; i <myString.length; i++) {
		//	System.out.println(myString[i]);
			int[][] twoDimensionalArray = { {0, 1, 2}, {2, 4, 6}, {2, 1, 0} };
			int num = twoDimensionalArray[1][2];
			System.out.println("num" + num);		
				
		}
		
		double n1 = 234;
		double n2 = 56;
		double sum = n1 / n2;
		//Round up 
		System.out.println("Sum:" + Math.ceil(sum));
				
		
	}

}
