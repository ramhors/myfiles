package com.revature.dto;

import java.util.Objects;

public class AddOrUpdateUsersDTO {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String userRole;
	
	public AddOrUpdateUsersDTO() {}

	public AddOrUpdateUsersDTO(String username, String password, String firstName, String lastName, String email,
			String userRole) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, lastName, password, username, userRole);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddOrUpdateUsersDTO other = (AddOrUpdateUsersDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username) && Objects.equals(userRole, other.userRole);
	}

	@Override
	public String toString() {
		return "addOrUpdateUsersDTO [userName=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", userRole=" + userRole + "]";
	};
	
	
}
