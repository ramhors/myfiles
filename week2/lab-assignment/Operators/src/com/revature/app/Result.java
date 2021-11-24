package com.revature.app;



public class Result {
	
	public static void solve(double meal_cost, int tip_percent, int tax_percent) {
		meal_cost = 12.00;
		tip_percent = 12/100 * 20;
		tax_percent = 8 / 100 * 20;
		
		double total_cost = meal_cost + tip_percent + tax_percent;
		Math.round(total_cost);
		System.out.println(total_cost);
	}

}
