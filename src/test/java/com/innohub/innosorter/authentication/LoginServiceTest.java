package com.innohub.innosorter.authentication;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.innohub.innosorter.entity.User;

public class LoginServiceTest {
	
	private LoginService loginService;
	
	
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Test
	public void shouldNotLoginUserWithIncorrectUserNamePassword() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName="";
		String password="";
		
		loginService.loginUser(userName, password);
		
	}
	
	@Test
	public void shouldUserSignOutSuccessfullyIfLoggedIn() {
		
		loginService= new LoginService();
		User user=new User();

		loginService.checkUserLoggedIn(user);
		
		
		
	}
	
	
	
}