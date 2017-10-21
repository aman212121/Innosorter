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
		//all digits
		String result = authenticationService.addNewUser("Aman", "12345678");
		assertEquals(result, RegistrationServiceDao.badPasswordMessage);
		//all lowercase
		result = authenticationService.addNewUser("Aman", "abcdefgh");
		assertEquals(result, RegistrationServiceDao.badPasswordMessage);
		//all uppercase
		result = authenticationService.addNewUser("Aman", "ABCDEFGHI");
		assertEquals(result, RegistrationServiceDao.badPasswordMessage);
		//not enough characters
		result = authenticationService.addNewUser("Aman", "1234567");
		assertEquals(result, RegistrationServiceDao.badPasswordMessage);
		//correct password
		result = authenticationService.addNewUser("NotAman", "Abc12345");
		assertEquals(result, RegistrationServiceDao.successMessage);
	}
	
	@Test
	public void testShouldNewPasswordBeDifferentFromOldPassword(){
		
		authenticationService.addNewUser("NotAman", "Abc12345");
		String result = authenticationService.updatePassword("Aman", "Abc12345", "Abc12345");
		assertEquals(result, authenticationService.unsuccessfulPasswordUpdateMessage);
		result = authenticationService.updatePassword("Aman", "Abc12345", "Bcd23456");
		assertEquals(result, authenticationService.successfulPasswordUpdateMessage);
	}
}
