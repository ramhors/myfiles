package com.revature.model;

public class Lion extends Cat{
	
	public Lion(String name) {
		super(name);
	}
	
	//You can have multipe level of overridging
	public void makeNoise() {
		System.out.println("ROOOOOOOOOOOR");
	}

}
