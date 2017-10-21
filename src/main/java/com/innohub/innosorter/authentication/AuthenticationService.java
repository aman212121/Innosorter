package com.innohub.innosorter.authentication;

public class AuthenticationService {

	protected String nullPasswordMessage = "Null Password";
	RegistrationServiceDao registrationServiceDao = new RegistrationServiceDao();
	
	public String addNewUser(String username, String password) {
		
		//if the password is empty, return false
		if(password.isEmpty()){
			return nullPasswordMessage;
		}
		return registrationServiceDao.registerUser(username, password);
	}
}
