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
		
		String result = authenticationService.addNewUser("newUsername", "");
		assertEquals(result, authenticationService.nullPasswordMessage);
		
	}
	
	@Test
	public void testShouldNotAllowUserToHaveDuplicateUserName(){
		String result = "";
		authenticationService.addNewUser("Aman", "123456");
		result = authenticationService.addNewUser("Aman", "1234");
		assertEquals(result, RegistrationServiceDao.usernameAlreadyExistsMessage);
	}
}
