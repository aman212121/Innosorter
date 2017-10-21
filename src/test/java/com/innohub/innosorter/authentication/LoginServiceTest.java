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
		expected.expectMessage("User Name is invaled");
		
		loginService= new LoginService();
		String userName="";
		
		loginService.loginUser(userName);
		
	}
	
	@Test
	public void shouldNotLoginUserWithIncorrectPassword() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("Password is invaled");
		
		loginService= new LoginService();
		String password="";
		
		loginService.loginUser(password);
		
	}
	


}
