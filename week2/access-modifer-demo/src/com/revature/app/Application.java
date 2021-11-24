package com.revature.app;

import com.revature.model.Person;

public class Application extends Person{		
	

	public static void main(String[] args) {
		System.out.println(Person.numOfInstane);
		
		Person p1 = new Person("Bob","Smith",23.4, "234-234-3452",170);	
		
		Person p2 = new Person();
		
		System.out.println("Last Name: " + p1.getLastName());		
		
		System.out.println(p1.firstName);//Can be accessed
		
		Application a1 = new Application();
		
		System.out.println("Weight: " + p1.getWeight());
		
		System.out.println("Phone number: " + p1.getPhoneNumber());
		
	}	
	
		
}
