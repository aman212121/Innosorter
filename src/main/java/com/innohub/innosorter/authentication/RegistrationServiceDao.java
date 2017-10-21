package com.innohub.innosorter.authentication;

import java.util.HashMap;

public class RegistrationServiceDao {

	//stores username and password of the system
	private HashMap<String, String> systemUsers = new HashMap<String, String>();
	//return messages for creating a new user
	protected static String usernameAlreadyExistsMessage = "Username Already Exists";
	protected static String successMessage = "New User Successfully Added";

	public String registerUser(String username, String password) {
		
		if(systemUsers.containsKey(username)){
			return usernameAlreadyExistsMessage;
		}
		
		else{
			systemUsers.put(username, password);
			return successMessage;
		}
	}
}
