package com.revature.model;

public class Dog extends Animal {

	public Dog(String name) {
		super(name);
	}
	//Overriding method from anima clas
	public void makeNoise() {
		System.out.println("Bark");
	}
	
	public void play() {
		System.out.println(super.name + "is playing");
	}
	public void play(String game) {
		System.out.println(super.name + "is playing " + game);
	}
}

