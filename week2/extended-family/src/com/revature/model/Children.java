package com.revature.model;

public class Children extends Parent {	
	
	public int age;
	public String[] childrenList = {"Stella","Korri","Narcisse","Dina","Felana","Noah"};
	
	public Children(String firstName, String lastName, int age) {
		super(firstName, lastName);
	}
	
	public void printInfo(String firstName, String lastName, int age) {
		System.out.println("Child first name: " + firstName + " last name: " + lastName + " age: " + age);
	}
	
	public void printAllChildren() {
		System.out.println("List of the children: ");
		for(int i = 0; i < childrenList.length; i++) {
			System.out.print(childrenList[i] + ",");
		}
		System.out.println();
	}
}
