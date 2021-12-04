package com.revature.dto;

import java.util.Objects;

public class JoinTableDTO {

	private int id;
	private double amount;
	private String date;
	private String status;
	private int resolver;
	private String firstName;
	private String lastName;
	
	JoinTableDTO(){}

	public JoinTableDTO(int id, double amount, String date, String status, int resolver, String firstName,
			String lastName) {
		super();
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.status = status;
		this.resolver = resolver;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, date, firstName, id, lastName, resolver, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JoinTableDTO other = (JoinTableDTO) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(date, other.date) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && resolver == other.resolver
				&& Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "JoinTableDTO [id=" + id + ", amount=" + amount + ", date=" + date + ", status=" + status + ", resolver="
				+ resolver + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	};
	
	
}
