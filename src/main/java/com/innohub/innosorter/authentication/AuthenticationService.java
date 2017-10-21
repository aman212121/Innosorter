package com.innohub.innosorter.authentication;

public class AuthenticationService {

	public Boolean addNewUser(String username, String password) {
		
		Boolean result = false;
		//if the password is empty, return false
		if(password.isEmpty()){
			return result;
		}
		return true;
	}
}
