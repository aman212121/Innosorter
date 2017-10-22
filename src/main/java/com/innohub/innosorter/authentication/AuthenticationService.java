package com.innohub.innosorter.authentication;

import com.innohub.innosorter.util.ApplicationConstants;

public class AuthenticationService {

	RegistrationServiceDao registrationServiceDao = new RegistrationServiceDao();
	
	public String addNewUser(String username, String password) {
		
		if(username == null){
			if(password == null){
				throw new RuntimeException(ApplicationConstants.NULL_USERNAME_AND_PASSWORD_MSG);
			}
			throw new RuntimeException(ApplicationConstants.NULL_USERNAME_MSG);
		}
		if(username.isEmpty()){
			if(password.isEmpty()){
				throw new RuntimeException(ApplicationConstants.EMPTY_USERNAME_AND_PASSWORD_MSG);
			}
			throw new RuntimeException(ApplicationConstants.EMPTY_USERNAME_MSG);
		}
		if(password!= null){
			//if the password is an empty string
			if(password.isEmpty()){
				throw new RuntimeException(ApplicationConstants.EMPTY_PASSWORD_MSG);
			}
			return registrationServiceDao.registerUser(username, password);
		}
		else {
			throw new RuntimeException(ApplicationConstants.NULL_PASSWORD_MSG);
		}
	}

	public String updatePassword(String username, String oldPassword, String newPassword) {
		
		if(registrationServiceDao.systemUsers.containsKey(username)){
			if(registrationServiceDao.systemUsers.get(username).equals(oldPassword)){
				if(oldPassword.equals(newPassword)){
					throw new RuntimeException(ApplicationConstants.SAME_AS_PREVIOUS_PASSWORD_MSG);
				}
				else {
					registrationServiceDao.systemUsers.put(username, newPassword);
					return ApplicationConstants.SUCCESSFULLY_UPDATED_PASSWORD_MSG;
				}
			}
			else {
				throw new RuntimeException(ApplicationConstants.INVALID_PASSWORD_MSG);
			}
		}
		else {
			throw new RuntimeException(ApplicationConstants.INVALID_USERNAME_MSG);
		}
	}
}
