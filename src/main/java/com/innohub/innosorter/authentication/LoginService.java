package com.innohub.innosorter.authentication;

import com.innohub.innosorter.entity.User;

public class LoginService {
	
	public void loginUser(String username,String password) {
		
		RegistrationServiceDao registrationServiceDao=new RegistrationServiceDao();
		
		boolean isunamepwdCurrect = registrationServiceDao.validateUserNamePassword(username,password);
		
	}

	public void checkUserLoggedIn(User user) {
		
		// TODO Auto-generated method stub
		
	}
	
	

}
