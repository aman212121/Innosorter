package com.innohub.innosorter.authentication;

import java.util.HashMap;

import com.innohub.innosorter.entity.UserType;
import com.innohub.innosorter.util.ApplicationConstants;

public class RegistrationServiceDao {

	//stores username and password of the system
	protected HashMap<String, String> systemUsers = new HashMap<String, String>();

	public String registerUser(String username, String password, String userType) {
		
		int minLength =8;
		int maxLength = 18;
	    int digit =0;
	    int upCharCount =0;
	    int lowerCharCount =0;
		
	    if(username!= null){
	    	
	    	if(username.isEmpty()){
	    		throw new RuntimeException( ApplicationConstants.EMPTY_USERNAME_MSG);
	    	}
			if(systemUsers.containsKey(username)){
				throw new RuntimeException( ApplicationConstants.USERNAME_ALREADY_EXISTS_MSG);
			}
			else if (username.length() > maxLength){
				throw new RuntimeException( ApplicationConstants.LARGE_USERNAME_MSG);
			}
	    }
	    else {
	    	throw new RuntimeException( ApplicationConstants.NULL_USERNAME_MSG);
	    }
		
		if(userType!= null){
			if (userType.isEmpty()){
				throw new RuntimeException(ApplicationConstants.EMPTY_USER_TYPE_MSG);
			}
			else if(UserType.forValue(userType)== null) {
				throw new RuntimeException(ApplicationConstants.INVALID_USER_TYPE_MSG);
			}
			
		}else {
			throw new RuntimeException(ApplicationConstants.NULL_USER_TYPE_MSG);
		}
		if( password.length() > maxLength){
			throw new RuntimeException( ApplicationConstants.LARGE_PASSWORD_MSG);
		}
		else if(password.length() >= minLength){
			
			for(int i = 0; i < password.length(); i++){
				char a = password.charAt(i);
				//if uppercase character found increase count
				if(Character.isUpperCase(a)){
					upCharCount++;
				}
				//if lowercase character found increase count
				if(Character.isLowerCase(a)){
					lowerCharCount++;
				}
				//if digit found increase count
				if(Character.isDigit(a)){
					digit++;
				}
			}
			//if all the conditions are met, add user
			if (digit > 0 && upCharCount >0 && lowerCharCount >0){
				systemUsers.put(username, password);
				return ApplicationConstants.SUCCESSFULLY_ADDED_USER_MSG;
			}
		}		
		throw new RuntimeException( ApplicationConstants.BAD_PASSWORD_MSG);
	}
	
	public boolean validateUserNamePassword(String username,String password,String role) {
		
		if(systemUsers.containsKey(username) && systemUsers.get(username).equals(password)){
			return true;
		}
		  return false;
	}

	public String getUserRole() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

	