package com.revature.app;

public class MyClass {

	public static void main(String[] args) {
		/***************
		 * Printing my name
		 ******************/
		String firstName = "Jean Maurice";
		String lastName = " Rakotoarimanana";
		
		System.out.println("MY FULL NAME: ");
		
		/****************************
		 * UNDERLINING PRINTED TEXT
		 ****************************/
		System.out.println("\t\033[4;2m" + "First name: " + "\033[0m" + firstName + ", ");
		
		System.out.println("\t\033[4;2m" + "Last name: " + "\033[0m" + lastName);

	}

}
