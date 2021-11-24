package com.revature.app;

public class Main {
	
	public static void main(String... args) {
		
		System.out.println(getSum(10.5,56.2,45.6));
		
		System.out.println(getSum(10.5,56.2,45.6,23.7,45.34));
		
	}
	
	public static double getSum(double a, double b) {
		System.out.println("getSum with 2 doubles invoke");
		return a + b;
	}
	//Two rules for var-args:
	// 1. var-args must go at the end of the parameters list
	//2. you can only have on var-args for a parameter
	
	//Implement a var-args 
	public static double getSum(double d1, double d2, double...numbers) {
		
		double sum = 0;
		sum += d1;
		sum += d2;
		
		for (double num: numbers) {
			sum += num;
		}
		
		return sum;
	}

}
