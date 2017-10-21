package com.innohub.innosorter.authentication;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AuthenticationServiceTest {

	AuthenticationService authenticationService;
	@Before
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
		authenticationService.addNewUser("Aman", "A123456");
		result = authenticationService.addNewUser("Aman", "B1234");
		assertEquals(result, RegistrationServiceDao.usernameAlreadyExistsMessage);
	}
	
	@Test
	public void testShouldNotAllowUserToSetPasswordWhichIsNotValid(){
		
		String result = authenticationService.addNewUser("Aman", "123456");
		assertEquals(result, RegistrationServiceDao.badPasswordMessage);
	}
}
