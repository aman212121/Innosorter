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
		assertEquals( authenticationService.nullPasswordMessage, result);	
	}
	
	@Test
	public void testShouldNotAllowUserToHaveDuplicateUserName(){

		authenticationService.addNewUser("Aman", "A123456fd");
		String result = authenticationService.addNewUser("Aman", "B1234trw");;
		assertEquals(RegistrationServiceDao.usernameAlreadyExistsMessage, result);
	}
	
	@Test
	public void testShouldNotAllowUserToSetWeakPassword(){
		//all digits
		String result = authenticationService.addNewUser("Aman", "12345678");
		assertEquals(RegistrationServiceDao.badPasswordMessage, result);
		//all lowercase
		result = authenticationService.addNewUser("Aman", "abcdefgh");
		assertEquals( RegistrationServiceDao.badPasswordMessage, result);
		//all uppercase
		result = authenticationService.addNewUser("Aman", "ABCDEFGHI");
		assertEquals(RegistrationServiceDao.badPasswordMessage, result);
		//not enough characters
		result = authenticationService.addNewUser("Aman", "1234567");
		assertEquals( RegistrationServiceDao.badPasswordMessage, result);
		//correct password
		result = authenticationService.addNewUser("NotAman", "Abc12345");
		assertEquals(RegistrationServiceDao.successMessage, result);
	}
	
	@Test
	public void testShouldNewPasswordBeDifferentFromOldPassword(){
		
		authenticationService.addNewUser("NotAman", "Abc12345");
		
		String result = authenticationService.updatePassword("NotAman", "Abc12345", "Abc12345");
		assertEquals(authenticationService.unsuccessfulPasswordUpdateMessage, result);
		
		result = authenticationService.updatePassword("NotAman", "Abc12345", "Bcd23456");
		assertEquals(authenticationService.successfulPasswordUpdateMessage, result);
		
		result = authenticationService.updatePassword("NotAman", "Abc12395", "Bcd23456");
		assertEquals(authenticationService.invalidPasswordMessage, result);
		
		result = authenticationService.updatePassword("Aman", "Abc12395", "Bcd23456");
		assertEquals(authenticationService.invalidUserMessage, result);
		
	}
}
