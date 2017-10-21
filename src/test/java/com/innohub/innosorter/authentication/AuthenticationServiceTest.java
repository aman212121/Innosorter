package com.innohub.innosorter.authentication;

import org.junit.Test;

import junit.framework.TestCase;

public class AuthenticationServiceTest extends TestCase {

	AuthenticationService authenticationService;
	
	@Override
	public void setUp(){
		authenticationService = new AuthenticationService();
	}
	
	@Test
	public void testShouldNotAllowUserToSetPasswordWhichIsNull(){
		
		Boolean result = authenticationService.addNewUser("newUsername", "");
		assertFalse(result);
		
	}
	
	@Test
	public void testShouldNotAllowUserToHaveDuplicateUserName(){
		Boolean result;
		authenticationService.addNewUser("Username", "123456");
		result = authenticationService.addNewUser("Username", "123456");
		
		assertFalse(result);
	}
}
