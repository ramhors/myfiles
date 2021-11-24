package com.revature.service;

import java.util.ArrayList;

import com.revature.dao.PersonDAO;
import com.revature.model.Person;

public class PersonService {
	
	private PersonDAO personDAO;
	
	public void addPerson(Person person) {
		if(person.getAge() < 0) {
			System.out.println("Error: we can not add this person object");
			return ;
		}
		
		//Utilize a method from string class that ulitize a method from the string class that takes in what is known as a regular
		//expression
		if(!person.getPhoneNumber().matches("\\d{3}-\\d{3}-\\d{4}")) {
			System.out.println("Error : we can not");
			return;
		}
		this.personDAO.add(person);
	}
	public ArrayList<Person>getAll(){
		return this.personDAO.getAll();

}
	
}