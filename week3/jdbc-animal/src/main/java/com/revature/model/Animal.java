package com.revature.model;

import java.util.Objects;

public class Animal {
	
	private int id;
	private String name;
	private int age;
	private String bread;
	private int weight;
	
	
	public Animal() {}


	public Animal(int id, String name, int age, String bread, int weight) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.bread = bread;
		this.weight = weight;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getBread() {
		return bread;
	}


	public void setBread(String bread) {
		this.bread = bread;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	@Override
	public int hashCode() {
		return Objects.hash(age, bread, id, name, weight);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return age == other.age && Objects.equals(bread, other.bread) && id == other.id
				&& Objects.equals(name, other.name) && weight == other.weight;
	}


	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", age=" + age + ", bread=" + bread + ", weight=" + weight + "]";
	};
	
	
	

}
