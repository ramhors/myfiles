ypackage com.revature.app;

import java.util.Scanner;

public class Application {
	
	public static void main(String[] args) {
		
		String input = getInput("Enter a value: ");
		System.out.println("You entered: " + input);
	}

	private static String getInput(String prompt) {
		System.out.println(prompt);		
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		return input;
	}
}
