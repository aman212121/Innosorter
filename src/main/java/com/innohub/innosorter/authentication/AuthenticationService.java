package com.innohub.innosorter.authentication;

public class AuthenticationService {

	protected String emptyPasswordMessage = "Empty Password";
	protected String nullPasswordMessage = "Null Password";
	RegistrationServiceDao registrationServiceDao = new RegistrationServiceDao();
	protected String unsuccessfulPasswordUpdateMessage = "Same as old password";
	protected String successfulPasswordUpdateMessage = "Password has been updated";
	protected String invalidUserMessage = "Invalid Username";
	protected String invalidPasswordMessage = "Invalid Password";
	
	public String addNewUser(String username, String password) {
		
		if(password!= null){
			//if the password is an empty string
			if(password.isEmpty()){
				throw new RuntimeException(emptyPasswordMessage);
			}
			return registrationServiceDao.registerUser(username, password);
		}
		else {
			throw new RuntimeException(nullPasswordMessage);
		}
	}

	public String updatePassword(String username, String oldPassword, String newPassword) {
		
		if(registrationServiceDao.systemUsers.containsKey(username)){
			if(registrationServiceDao.systemUsers.get(username).equals(oldPassword)){
				if(oldPassword.equals(newPassword)){
					throw new RuntimeException(unsuccessfulPasswordUpdateMessage);
				}
				else {
					registrationServiceDao.systemUsers.put(username, newPassword);
					return successfulPasswordUpdateMessage;
				}
			}
			else {
				throw new RuntimeException(invalidPasswordMessage);
			}
		}
		else {
			throw new RuntimeException(invalidUserMessage);
		}
	}
}
