package com.innohub.innosorter.authentication;

import org.junit.Test;

import junit.framework.TestCase;

public class AuthenticationServiceTest extends TestCase {

	@Test
	public void testshouldNotAllowUserToSetPasswordWhichIsNull(){
		
		AuthenticationService authenticationService = new AuthenticationService();
		Boolean result = authenticationService.addNewUser("newUsername", "");
		assertFalse(result);
	}
}
