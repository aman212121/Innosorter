package com.innohub.innosorter.authentication;

public class AuthenticationService {

	protected String nullPasswordMessage = "Null Password";
	protected String successMessage = "New User Successfully Added";
	protected String usernameAlreadyExistsMessage = "Username Already Exists";
	
	public String addNewUser(String username, String password) {
		
		//if the password is empty, return false
		if(password.isEmpty()){
			return nullPasswordMessage;
		}
		
		return successMessage;
	}
}
