package com.revature.demo;

//Fuctional interface an interface that contains only 1 abstract method, only interface with only 1 abstract method is a functional interface

//The primary functional i

@FunctionalInterface
public interface Predicate<T> {
	
	public boolean test(T t);
	
	

}
