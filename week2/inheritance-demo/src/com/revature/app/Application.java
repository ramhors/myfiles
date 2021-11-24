package com.revature.app;

import com.revature.model.Animal;
import com.revature.model.Cat;
import com.revature.model.Dog;
import com.revature.model.Food;
import com.revature.model.Lion;

public class Application {

	public static void main(String[] args) {

		Food steack = new Food("Filler Mignon", 50);		
		
		
		Animal a1 = new Dog("Fido");
		a1.eat(steack);
		
		
		//Pring a generic animal Noises or Bark
		a1.makeNoise();
		
		Food fish = new Food("Tilapia", 50);
		
		a1 = new Cat("Whisker");
		a1.eat(fish);
		a1.makeNoise();
		
		a1 = new Lion("Simba");
		a1.makeNoise();
		
		Food boneNarrow = new Food("Bone Marrow", 10);
		Animal a1000 = new Dog("Sparky");
		a1000.eat(boneNarrow);
		a1000.makeNoise();
		//Creat a scor dog objec
		a1 = new Dog("Sparky");
		
		if(a1000 instanceof Dog) {
			((Dog) a1000).play();
			((Dog) a1000).play("Fetch");
		}
		
		Dog d1000 = new Dog("Clifford");
		d1000.play();
		
		//Equals method
		String s1 = "hello"; //Stores in pool
		String s2 = new String("hello"); //stores in the heap
		System.out.println("s1 === s2: " + (s1 == s2));
		System.out.println("is.equals(s2: " + s1.equals(s2));
		
		//Creating two different animal object with the same name
		Animal a10000 = new Dog("Fido");
		Animal a10001 = new Dog("Fido");
		
		/*Print true bbecause even though they are different objects from each other 
		 * they have the same name and energyLevel
		 */
		System.out.println(a10000.equals(a10001));
		System.out.println(a10000);
		
		String m = "5";
		String s = "5";
		System.out.println("Equality :" + m.equals(s));
	}

}
