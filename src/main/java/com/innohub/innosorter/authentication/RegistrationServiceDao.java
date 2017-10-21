package com.innohub.innosorter.authentication;

import java.util.HashMap;

public class RegistrationServiceDao {

	//stores username and password of the system
	protected HashMap<String, String> systemUsers = new HashMap<String, String>();
	//return messages for creating a new user
	public static String usernameAlreadyExistsMessage = "Username Already Exists";
	public static String successMessage = "New User Successfully Added";
	public static String badPasswordMessage = "Weak Password";

	public String registerUser(String username, String password) {
		
		if(systemUsers.containsKey(username)){
			throw new RuntimeException( usernameAlreadyExistsMessage);
		}
		return successMessage;
	}
}
