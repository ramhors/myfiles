package com.revature.app;

import com.revature.model.Children;
import com.revature.model.Dog;
import com.revature.model.Family;
import com.revature.model.GrandParent;
import com.revature.model.Parent;

public class Main {

	public static void main(String[] args) {
		
		Family grandpa = new GrandParent(10,96);
		Parent parent1 = new Parent("Patrick", "Smith");
		Children kids = new Children("Benja","Smith", 12);
		Dog dog = new Dog(50,5,"Mickey");
		
		kids.printAllChildren();
		dog.printInfo("Mickey", 5, 78);
		kids.printInfo("Benja", "Smith", 12);
		parent1.printInfo("Smith", "Ben");
		grandpa.printInfo(10, 96);	
				
		
	}
	
	
}
