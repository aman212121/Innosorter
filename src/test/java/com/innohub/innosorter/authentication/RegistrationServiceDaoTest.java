package com.innohub.innosorter.authentication;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RegistrationServiceDaoTest {

	RegistrationServiceDao registrationServiceDao;
	@Before
	public void setUp(){
		registrationServiceDao = new RegistrationServiceDao();
	}
	
	@Rule 
	public ExpectedException expected = ExpectedException.none();
	
	@Test 
	public void shouldSuccessfullyRegisterNewUser(){
		
		String result = registrationServiceDao.registerUser("NotAman", "Abc12345");
		assertEquals(RegistrationServiceDao.successMessage, result);
	}
	
	@Test 
	public void shouldThrowExceptionWhenPasswordContainsOnlyDigits(){
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(RegistrationServiceDao.badPasswordMessage);
		
		registrationServiceDao.registerUser("Aman", "12345678");
	}
	
	@Test 
	public void shouldThrowExceptionWhenPasswordContainsOnlyLowerCase(){
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(RegistrationServiceDao.badPasswordMessage);
		
		registrationServiceDao.registerUser("Aman", "abcdefgh");
	}
	
	@Test 
	public void shouldThrowExceptionWhenPasswordContainsOnlyUpperCase(){
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(RegistrationServiceDao.badPasswordMessage);
		
		registrationServiceDao.registerUser("Aman", "abcdefgh");
	}
	
	@Test 
	public void shouldThrowExceptionWhenPasswordLengthIsLowerThanMinimum(){
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(RegistrationServiceDao.badPasswordMessage);
		
		registrationServiceDao.registerUser("Aman", "1234567");
	}
	
}
