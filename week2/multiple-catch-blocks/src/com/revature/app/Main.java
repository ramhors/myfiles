package com.revature.app;

public class Main {
			// USING MULTIPLE CATCH BLOCKS
	public static void main(String[] args) {
		
		try {
			String welcome = "Welcome";
			//Convert "Welcome" to an array
			char[] chars = welcome.toCharArray();
			char lastChar = chars[chars.length - 1];
			System.out.println("Last char: " + lastChar);
			
			for(int i = 0;  i < chars.length; i++) {
				System.out.print(chars[i] + " ");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Array index exception");
			e.printStackTrace();
			return;
		}catch (NullPointerException e) {
			System.out.println("Null exception");
			e.printStackTrace();
			return;
		}catch (Exception e) {
			System.out.println("Null exception");
		}
		System.out.println("Code ran successfully");

	}

}
