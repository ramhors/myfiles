package com.revature.service;

import java.sql.SQLException;

import javax.security.auth.login.FailedLoginException;

import com.revature.dao.UserDAO;
import com.revature.dto.AddOrUpdateUsersDTO;
import com.revature.exception.InvalidParameterException;
import com.revature.exception.UsersNotFoundException;
import com.revature.model.User;

public class UserService {
	
	private UserDAO userDao;
	
	public UserService() {
		this.userDao = new UserDAO();
	}
	
	public User getUserByUsernameAndPassword(String username, String password) throws SQLException, FailedLoginException {
		
		User user = this.userDao.getUserByUsernameAndPassword(username, password);
		
		if (user == null) {
			throw new FailedLoginException("Incorrect username and/or password");
		}
		return user;
	}

	public User addUser(AddOrUpdateUsersDTO dto) throws InvalidParameterException,UsersNotFoundException, SQLException {
		
	
		if(dto.getUserName().trim().equals("")) {
			throw new InvalidParameterException("User name can not be empty");
		}
		if(dto.getPassword().trim().equals("")) {
			throw new InvalidParameterException("Password field can not be empty   ");
		}
		if(dto.getFirstName().trim().equals("")) {
			throw new InvalidParameterException("First name field can not be empty");
		}
		if(dto.getLastName().trim().equals("")) {
			throw new InvalidParameterException("Last name field can not be empty");
		}
		if(dto.getEmail().trim().equals("")) {
			throw new InvalidParameterException("Email field can not be empty");
		}
		if(dto.getUserRole().trim().equals("")) {
			throw new InvalidParameterException("User Role field can not be empty");
		}
		if(dto.getUserName().trim().equals("") || dto.getPassword().trim().equals("") || dto.getFirstName().trim().equals("")||
		
		dto.getLastName().trim().equals("") || dto.getEmail().trim().equals("") || dto.getUserRole().trim().equals("")) {
			
			throw new InvalidParameterException("One of the field is empty");
		}
		//Trimming the leading and trailing whitespace
		dto.setUserName(dto.getUserName().trim());
		dto.setPassword(dto.getPassword().trim());
		dto.setFirstName(dto.getFirstName().trim());
		dto.setLastName(dto.getLastName().trim());
		dto.setEmail(dto.getEmail().trim());
		dto.setUserRole(dto.getUserRole().trim());
		
		User userInserted = this.userDao.addUsers(dto);
		return userInserted;		
	
	}
}
