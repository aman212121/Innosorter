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

		authenticationService.addNewUser("Aman", "A123456fd");
		String result = authenticationService.addNewUser("Aman", "B1234trw");;
		assertEquals(result, RegistrationServiceDao.usernameAlreadyExistsMessage);
	}
	
	@Test
	public void testShouldNotAllowUserToSetWeakPassword(){
		String result = authenticationService.addNewUser("Aman", "123456");
		assertEquals(result, RegistrationServiceDao.badPasswordMessage);
	}
}
