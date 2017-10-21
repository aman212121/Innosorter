package com.innohub.innosorter.authentication;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AuthenticationServiceTest {

	AuthenticationService authenticationService;
	@Before
	public void setUp(){
		authenticationService = new AuthenticationService();
	}
	
	@Rule 
	public ExpectedException expected = ExpectedException.none();
	
	@Test 
	public void shouldThrowExceptionWhenPasswordIsNull(){
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(authenticationService.nullPasswordMessage);
		
		authenticationService.addNewUser("newUsername", null);
	}
	
	@Test 
	public void shouldThrowExceptionWhenPasswordIsEmpty(){
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(authenticationService.emptyPasswordMessage);
		
		authenticationService.addNewUser("newUsername", "");
	}

	@Test
	public void shouldNotAllowUserToHaveDuplicateUserName(){
		
		authenticationService.addNewUser("Aman", "A123456fd");
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(RegistrationServiceDao.usernameAlreadyExistsMessage);
		
		authenticationService.addNewUser("Aman", "B1234trw");;
	}
	
	@Test
	public void shouldAllowUserToSetPassword(){
		
		//correct password
		String result = authenticationService.addNewUser("NotAman", "Abc12345");
		assertEquals(RegistrationServiceDao.successMessage, result);
	}
	
	@Test
	public void shouldThrowExceptionOfNewPasswordBeDifferentFromOldPasswordWhenUpdatingPassword(){
		
		authenticationService.addNewUser("NotAman", "Abc12345");
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(authenticationService.unsuccessfulPasswordUpdateMessage);
		
		authenticationService.updatePassword("NotAman", "Abc12345", "Abc12345");
	}
	
	@Test
	public void shouldSuccessfullyChangeOldPasswordToNewPasswordWhenUpdatingPassword(){
		
		authenticationService.addNewUser("NotAman", "Abc12345");
		
		String result = authenticationService.updatePassword("NotAman", "Abc12345", "Bcd23456");
		assertEquals(authenticationService.successfulPasswordUpdateMessage, result);
	}
	
	@Test
	public void shouldThrowExceptionOfIncorrectPasswordWhenUpdatingPassword(){
		
		authenticationService.addNewUser("NotAman", "Abc12345");
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(authenticationService.invalidPasswordMessage);
		
		authenticationService.updatePassword("NotAman", "Abc12395", "Bcd23456");
	}
	
	@Test
	public void shouldThrowExceptionOfInvalidUserWhenUpdatingPassword(){
		
		authenticationService.addNewUser("NotAman", "Abc12345");
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(authenticationService.invalidUserMessage);
		
		authenticationService.updatePassword("Aman", "Abc12395", "Bcd23456");
		
	}
}
