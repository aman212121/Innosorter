package com.innohub.innosorter.authentication;

import com.innohub.innosorter.util.ApplicationConstants;

public class UserNamePasswardVerificationService {
	
	int minLength =8;
	int maxLength = 18;
	
	public void checkUsernameLength(String userName) {
		
		if (userName.length() >= maxLength){
			throw new RuntimeException(ApplicationConstants.LARGE_USERNAME_MSG);
		}
	}
	
	public void checkPasswordLength(String password) {
		if (password.length() >= maxLength){
			throw new RuntimeException(ApplicationConstants.LARGE_PASSWORD_MSG);
		}
	}
	

}
