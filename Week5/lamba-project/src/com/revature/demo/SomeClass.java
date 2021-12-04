package com.revature.demo;

public class SomeClass implements Predicate<String>{

	@Override
	public boolean test(String t) {
		//This method will check for the length of the string, and if ti exceeds 10 characters return false
		
		if(t.length() > 10) {
			return false;
		}
		return true;
	}

}
