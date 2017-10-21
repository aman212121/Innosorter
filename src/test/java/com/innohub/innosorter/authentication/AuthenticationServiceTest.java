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
	public void testshouldNotAllowUserToSetPasswordWhichIsNull(){
		
		Boolean result = authenticationService.addNewUser("newUsername", "");
		assertFalse(result);
	}
	
}
