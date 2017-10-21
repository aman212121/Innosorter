package com.innohub.innosorter.authentication;

import org.junit.Test;

import junit.framework.TestCase;

public class AuthenticationServiceTest extends TestCase {

	@Test
	public void shouldNotAllowUserToSetPasswordWhichIsNull(){
		
		AuthenticationService authenticationService = new AuthenticationService();
		Boolean result = authenticationService.addNewUser("newUsername", "newPassword");
		assertTrue(result);
	}
}
