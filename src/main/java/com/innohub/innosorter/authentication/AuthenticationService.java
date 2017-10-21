package com.innohub.innosorter.authentication;

public class AuthenticationService {

	protected String nullPasswordMessage = "Null Password";
	RegistrationServiceDao registrationServiceDao = new RegistrationServiceDao();
	protected static String unsuccessfulPasswordUpdateMessage = "Same as old password";
	protected static String successfulPasswordUpdateMessage = "Password has been updated";
	protected static String invalidUserMessage = "Invalid Username";
	protected static String invalidPasswordMessage = "Invalid Password";
	
	public String addNewUser(String username, String password) {
		
		//if the password is empty, return false
		if(password.isEmpty()){
			return nullPasswordMessage;
		}
		return registrationServiceDao.registerUser(username, password);
	}

	public String updatePassword(String username, String oldPassword, String newPassword) {
		
		if(registrationServiceDao.systemUsers.containsKey(username)){
			if(registrationServiceDao.systemUsers.get(username).equals(oldPassword)){
				if(oldPassword.equals(newPassword)){
				     return unsuccessfulPasswordUpdateMessage;
				}
				else {
					registrationServiceDao.systemUsers.put(username, newPassword);
					return successfulPasswordUpdateMessage;
				}
			}
			else {
				return invalidPasswordMessage;
			}
		}
		else {
			return invalidUserMessage;
		}
	}
}
