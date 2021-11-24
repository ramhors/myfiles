package com.revature.app;

public class Varargs {

	public static void main(String[] args) {
		
		String item1 = "Apples";
		String item2 = "Mango";
		String item3 = "Pears";
		String item4 = "Banana";
		String item5 = "Peach";
		// String[] shopping = {"Bread","Milk","Eggs","Banana"};	
		
		printShoppingList(item1, item2,item3);
		printShoppingList("Bread","Milk","Eggs","Banana");
	}
	/*******************************************************************************
	 * 
	 * We don't need to repeat overloading the method
	 * 
	private static void printShoppingList(String string1, String string2) {
		System.out.println("Shopping List");
		System.out.println("1. " + string1);
		System.out.println("2. " + string2);
	}
	//Overloading the method to pring more items
	private static void printShoppingList(String string1, String string2, String string3) {
		System.out.println("Shopping List");
		System.out.println("1. " + string1);
		System.out.println("2. " + string2);
		System.out.println("3. " + string3);
	}
	
	****************************************************************************************/
	
	//This method wil take as many arguments as we want
	
	private static void printShoppingList(String... items) {
		System.out.println("Shopping List");
		for(int i = 0; i < items.length; i++) {
			System.out.println(i + 1 + ": " + items[i]);
		}
		System.out.println();
	}

}
