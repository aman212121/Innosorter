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
	public void shouldNotLoginWhenUserNameIsIncorrectForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName="test";
		String password="123456@#A";
		String role="Admin";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenPasswordIsIncorrectForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName="TestUserAdmin";
		String password="1234";
		String role="Admin";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenUserNameIsIncorrectForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName="Test";
		String password="123456@#A";
		String role="Developer";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenPasswordIsIncorrectForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName="TestUserDev";
		String password="1234";
		String role="Developer";
		
		loginService.loginUser(userName, password,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenUserNamePassowrdInceorrectForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName="Test";
		String password="1234";
		String role="Admin";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenUserNamePassowrdInceorrectForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName="Test";
		String password="1234";
		String role="Developer";
		
		loginService.loginUser(userName, password,role);
		
	}
	
	
	@Test
	public void shouldNotLoginWhenUserNameIsEmptyForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName="";
		String password="123456@#A";
		String role="Admin";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenUserNameIsEmptyForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName="";
		String password="123456@#A";
		String role="Developer";
		
		loginService.loginUser(userName, password,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenUserNameIsNullForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName=null;
		String password="123456@#A";
		String role="Admin";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenUserNameIsNullForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName=null;
		String password="123456@#A";
		String role="Developer";
		
		loginService.loginUser(userName, password,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenPasswordIsEmptyForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName="TestUserAdmin";
		String password="";
		String role="Admin";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenPasswordIsEmptyForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName="TestUserDev";
		String password="";
		String role="Developer";
		
		loginService.loginUser(userName, password,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenPasswordIsNullForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName="TestUserAdmin";
		String password= null;
		String role="Admin";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenPasswordIsNullForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName="TestUserDev";
		String password= null;
		String role="Developer";
		
		loginService.loginUser(userName, password,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenUsernamePasswordAreNullForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName=null;
		String password=null;
		String role="Admin";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenUsernamePasswordAreNullForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName=null;
		String password= null;
		String role="Developer";
		
		loginService.loginUser(userName, password,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenUsernamePasswordAreEmptyForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName="";
		String password= "";
		String role="Admin";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenUsernamePasswordAreEmptyForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Name or Password is invaled");
		
		loginService= new LoginService();
		String userName="";
		String password= "";
		String role="Developer";
		
		loginService.loginUser(userName, password,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenUsernameIsOverTheCharacterLimitForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User name is too long");
		
		loginService= new LoginService();
		
		String userName="TestUser123Admin";
		String role="Admin";
		
		loginService.checkUserName(userName,role);
		
	}
	@Test
	public void shouldNotLoginWhenUsernameIsOverTheCharacterLimitForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User name is too long");
		
		loginService= new LoginService();
		
		String userName="TestUser123Admin";
		String role="Developer";
		
		loginService.checkUserName(userName,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenPasswordIsOverTheCharacterLimitForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("Password is too long");
		
		loginService= new LoginService();
		
		String password= "123456789@#$AA12345789";
		String role="Admin";
		
		loginService.checkPassword(password,role);
		
	}
	@Test
	public void shouldNotLoginWhenPasswordIsOverTheCharacterLimitForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("Password is too long");
		
		loginService= new LoginService();
		
		String password= "123456789@#$AA12345789";
		String role="Developer";
		
		loginService.checkPassword(password,role);
		
	}
	@Test
	public void shouldNotLoginWhenUsernamePasswordAreOverTheCharacterLimitForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("Password is too long");
		
		loginService= new LoginService();
		String userName="TestUser123Admin";
		String password= "123456789@#$AA12345789";
		String role="Admin";
		
		loginService.checkPassword(password,role);
		
	}
	@Test
	public void shouldNotLoginWhenUsernamePasswordAreOverTheCharacterLimitForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("Password is too long");
		
		loginService= new LoginService();
		String userName="TestUser123Admin";
		String password= "123456789@#$AA12345789";
		String role="Developer";
		
		loginService.checkPassword(password,role);
		
	}
	
	@Test
	public void shouldLogoutSuccessfullyForAdminUserWhenLoggedIn () {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Logged out successfully");
		loginService= new LoginService();
		User usr= new User();
		loginService.loguot(usr);
		
	}
	@Test
	public void shouldLogoutSuccessfullyForDeveloperUSerWhenLoggedIn() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("User Logged out successfully");
		loginService= new LoginService();
		User usr= new User();
		loginService.loguot(usr);
		
	}
	
	
	@Test
	public void shouldThrowExceptionForAdminUserWhenNotLoggedIn () {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("Sorry User is Not LoggedIn");
		loginService= new LoginService();
		User usr= new User();
		loginService.checkUserLoggedIn(usr);
		
	}
	@Test
	public void shouldThrowExceptionForDeveloperUserWhenNotLoggedIn() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage("Sorry User is Not LoggedIn");
		loginService= new LoginService();
		User usr= new User();
		loginService.checkUserLoggedIn(usr);
		
	}
	
//	@Test
//	public void shouldAllowAdminUserToLogInAfterLogout () {
//		
//		expected.expect(RuntimeException.class);
//		expected.expectMessage("Sorry User is Not LoggedIn");
//		
//		loginService= new LoginService();
//		User usr= new User();
//		loginService.loguot(usr);
//		
//	}
//	@Test
//	public void shouldAllowDeveloperUserToLogInAfterLogout() {
//		
//		expected.expect(RuntimeException.class);
//		expected.expectMessage("Sorry User is Not LoggedIn");
//		
//		loginService= new LoginService();
//		User usr= new User();
//		loginService.loguot(usr);
//		
//	}
	
	
	

	
	
}