package com.revature.dao;

import java.util.ArrayList;

import com.revature.model.Person;

public class PersonDAO {
	
	private ArrayList<Person> people;
	
	//Constructor
	public PersonDAO() {
		this.people = new ArrayList();
	}
	
	public void add(Person per) {
		this.people.add(per);
	}
	public ArrayList<Person>getAll(){
		return this.people;
	}

}
