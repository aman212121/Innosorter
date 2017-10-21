package com.innohub.innosorter.authentication;

public class LoginService {
	
	public void loginUser(String username,String password) {
		
		RegistrationServiceDao registrationServiceDao=new RegistrationServiceDao();
		
		boolean isunamepwdCurrect = registrationServiceDao.validateUserNamePassword(username,password);
		
	}

}
