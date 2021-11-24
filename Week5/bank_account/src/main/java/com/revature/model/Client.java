package com.revature.model;

import java.util.Objects;

public class Client {

	private int clientId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private int clientAge;	
	
	public Client() {}

	public Client(int clientId, String firstName, String lastName, String phoneNumber, int clientAge) {
		super();
		this.clientId = clientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.clientAge = clientAge;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
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
		return Objects.hash(clientAge, clientId, firstName, lastName, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return clientAge == other.clientAge && clientId == other.clientId && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(phoneNumber, other.phoneNumber);
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", clientAge=" + clientAge + "]";
	};
	
	
	
	
}
