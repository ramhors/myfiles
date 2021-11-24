
public class MathematicalOperators {

	public static void main(String[] args) {

		int x = 5;
		int y = 3;
		int z;
		float f = 3.6f;
		int i = 22;
		//Addition
		z = x + 5;
		System.out.println("Addition result: " + z );
		//Subtraction
		z = x -5;
		System.out.println("Subtraction result: " + z);
		//Multiplication
		z = x * y;
		System.out.println("Multiplication result: " + z);
		//Division
		z = x / y;
		System.out.println("Division result: " + z);
		//Modulus
		z = x % y;
		System.out.println("Modulus result: " + z);
		//Float vs. int
		double result =  Math.round(f + i);
		System.out.println("Float result: " + result);
	}

}
