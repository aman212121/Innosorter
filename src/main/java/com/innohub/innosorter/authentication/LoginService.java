package com.innohub.innosorter.authentication;

import com.innohub.innosorter.entity.User;

public class LoginService {
	RegistrationServiceDao registrationServiceDao=new RegistrationServiceDao();

	public void loginUser(String username,String password,String role) {
		
		
		boolean isunamepwdCurrect = registrationServiceDao.validateUserNamePassword(username,password,role);
		
	}
	
	public void checkUserName(String username,String role) {
		
		//if(registrationServiceDao.getUserRole()==role) {
			
			if (username.length() <= 18){
				throw new RuntimeException("User name is too long");
			}
		//}
		
		
		
	}
	public void checkPassword(String password,String role) {
		
		//if(registrationServiceDao.getUserRole()==role) {
			
//			if (password.length()<=8){
//				throw new RuntimeException("Password is too short");
//			}else 
				if (password.length() >= 18) {
					throw new RuntimeException("Password is too long");
				}
		//}
		
		
	}
	
	public void checkUserLoggedIn(User user) {
		throw new RuntimeException("Sorry User is Not LoggedIn");
		
	}

	public void loguot(User usr) {
		throw new RuntimeException("User Logged out successfully");

	}
	
	

}
