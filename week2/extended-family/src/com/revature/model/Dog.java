package com.revature.model;

public class Dog extends Family{
	
	public int age;
	public String name;
	
	public Dog(int size, int age, String name) {
		super(size);
		this.age = age;
		this.name = name;
	}
	
	public void printInfo(String name, int age, int size) {
		System.out.println("Dog's name: " + name + ", Age: " + age + ", Size: " + size);
	}

	
}
