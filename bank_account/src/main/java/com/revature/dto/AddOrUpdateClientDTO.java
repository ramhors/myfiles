package com.revature.dto;

import java.util.Objects;

public class AddOrUpdateClientDTO {

	private String firstName;
	private String lastName;
	private String phoneNumber;
	private int clientAge;
	
	AddOrUpdateClientDTO(){};
	
	public AddOrUpdateClientDTO(String firstName,String lastName,String phoneNumber,int clientAge) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.clientAge = clientAge;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getClientAge() {
		return clientAge;
	}
	public void setClientAge(int clientAge) {
		this.clientAge = clientAge;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddOrUpdateClientDTO other = (AddOrUpdateClientDTO) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}

	@Override
	public String toString() {
		return "EditOrUpdateCustomer [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
	
}
