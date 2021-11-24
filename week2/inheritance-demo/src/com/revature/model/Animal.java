package com.revature.model;

import java.util.Objects;

/******************************************************************************
 * We want to define a properties and behaviors tha are universe to all Animals
 * here
 *********************************************************************************/
public class Animal {

	public String name;
	public double energieLevel;
	
	public Animal (String name) {
		this.name = name;
		this.energieLevel = energieLevel;
	}

	public void makeNoise() {
		System.out.println("GENERIC ANIMAL NOISES");
	}
	public void eat(Food food) {
		this.energieLevel += food.nourishmentValue;
		System.out.println(this.name + " ate " + food.foodName + " and increased its enegy level by " + this.energieLevel);
	}

	@Override
	public int hashCode() {
		return Objects.hash(energieLevel, name);
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
		return Double.doubleToLongBits(energieLevel) == Double.doubleToLongBits(other.energieLevel)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Animal [name=" + name + ", energieLevel=" + energieLevel + "]";
	}
	
}
