package com.innohub.innosorter.authentication;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LoginServiceTest {
	
	private LoginService loginService;
	
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Test
	public void shouldNotLoginUserWithIncorrectUserName() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName="";
		String password="";
		
		loginService.loginUser(userName, password);
		
	}
	
	
	


}
