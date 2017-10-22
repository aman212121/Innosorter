package com.innohub.innosorter.authentication;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.innohub.innosorter.util.ApplicationConstants;

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
		
		String result = registrationServiceDao.registerUser("NotAman", "Abc12345", "developer");
		assertEquals(ApplicationConstants.SUCCESSFULLY_ADDED_USER_MSG, result);
	}
	
	@Test 
	public void shouldThrowExceptionWhenPasswordContainsOnlyDigits(){
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.BAD_PASSWORD_MSG);
		
		registrationServiceDao.registerUser("Aman", "12345678" , "developer" );
	}
	
	@Test 
	public void shouldThrowExceptionWhenPasswordContainsOnlyLowerCase(){
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.BAD_PASSWORD_MSG);
		
		registrationServiceDao.registerUser("Aman", "abcdefgh", "developer");
	}
	
	@Test 
	public void shouldThrowExceptionWhenPasswordContainsOnlyUpperCase(){
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.BAD_PASSWORD_MSG);
		
		registrationServiceDao.registerUser("Aman", "ABCDEFGHI", "developer");
	}
	
	@Test 
	public void shouldThrowExceptionWhenPasswordLengthIsLowerThanMinimum(){
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.BAD_PASSWORD_MSG);
		
		registrationServiceDao.registerUser("Aman", "1234567", "developer");
	}
	
	@Test 
	public void shouldThrowExcetionWhenUserTypeIsEmpty(){
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.EMPTY_USER_TYPE_MSG);
		
		registrationServiceDao.registerUser("Aman", "1234567Ab", "");
	}
	
	@Test 
	public void shouldThrowExcetionWhenUserTypeIsNull(){
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.NULL_USER_TYPE_MSG);
		
		registrationServiceDao.registerUser("Aman", "1234567Ab", null);
	}
	
	@Test 
	public void shouldThrowExcetionWhenUserTypeDoesNotExist(){
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.INVALID_USER_TYPE_MSG);
		
		registrationServiceDao.registerUser("Aman", "1234567Ab", "not developer");
	}
}
