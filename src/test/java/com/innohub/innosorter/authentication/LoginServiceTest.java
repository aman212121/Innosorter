package com.innohub.innosorter.authentication;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.innohub.innosorter.entity.User;
import com.innohub.innosorter.util.ApplicationConstants;

public class LoginServiceTest {
	
	private LoginService loginService;
	
	
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Test
	public void shouldNotLoginWhenUserNameIsIncorrectForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LOGIN_ERROR_MSG);
		
		loginService= new LoginService();
		String userName="test";
		String password="123456@#A";
		String role="Admin";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenPasswordIsIncorrectForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LOGIN_ERROR_MSG);
		
		loginService= new LoginService();
		String userName="TestUserAdmin";
		String password="1234";
		String role="Admin";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenUserNameIsIncorrectForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LOGIN_ERROR_MSG);
		
		loginService= new LoginService();
		String userName="Test";
		String password="123456@#A";
		String role="Developer";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenPasswordIsIncorrectForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LOGIN_ERROR_MSG);
		
		loginService= new LoginService();
		String userName="TestUserDev";
		String password="1234";
		String role="Developer";
		
		loginService.loginUser(userName, password,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenUserNamePassowrdInceorrectForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LOGIN_ERROR_MSG);
		
		loginService= new LoginService();
		String userName="Test";
		String password="1234";
		String role="Admin";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenUserNamePassowrdInceorrectForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LOGIN_ERROR_MSG);
		
		loginService= new LoginService();
		String userName="Test";
		String password="1234";
		String role="Developer";
		
		loginService.loginUser(userName, password,role);
		
	}
	
	
	@Test
	public void shouldNotLoginWhenUserNameIsEmptyForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LOGIN_ERROR_MSG);
		
		loginService= new LoginService();
		String userName="";
		String password="123456@#A";
		String role="Admin";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenUserNameIsEmptyForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LOGIN_ERROR_MSG);
		
		loginService= new LoginService();
		String userName="";
		String password="123456@#A";
		String role="Developer";
		
		loginService.loginUser(userName, password,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenUserNameIsNullForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LOGIN_ERROR_MSG);
		
		loginService= new LoginService();
		String userName=null;
		String password="123456@#A";
		String role="Admin";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenUserNameIsNullForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LOGIN_ERROR_MSG);
		
		loginService= new LoginService();
		String userName=null;
		String password="123456@#A";
		String role="Developer";
		
		loginService.loginUser(userName, password,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenPasswordIsEmptyForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LOGIN_ERROR_MSG);
		
		loginService= new LoginService();
		String userName="TestUserAdmin";
		String password="";
		String role="Admin";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenPasswordIsEmptyForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LOGIN_ERROR_MSG);
		
		loginService= new LoginService();
		String userName="TestUserDev";
		String password="";
		String role="Developer";
		
		loginService.loginUser(userName, password,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenPasswordIsNullForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LOGIN_ERROR_MSG);
		
		loginService= new LoginService();
		String userName="TestUserAdmin";
		String password= null;
		String role="Admin";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenPasswordIsNullForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LOGIN_ERROR_MSG);
		
		loginService= new LoginService();
		String userName="TestUserDev";
		String password= null;
		String role="Developer";
		
		loginService.loginUser(userName, password,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenUsernamePasswordAreNullForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LOGIN_ERROR_MSG);
		
		loginService= new LoginService();
		String userName=null;
		String password=null;
		String role="Admin";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenUsernamePasswordAreNullForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LOGIN_ERROR_MSG);
		
		loginService= new LoginService();
		String userName=null;
		String password= null;
		String role="Developer";
		
		loginService.loginUser(userName, password,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenUsernamePasswordAreEmptyForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LOGIN_ERROR_MSG);
		
		loginService= new LoginService();
		String userName="";
		String password= "";
		String role="Admin";
		
		loginService.loginUser(userName, password,role);
		
	}
	@Test
	public void shouldNotLoginWhenUsernamePasswordAreEmptyForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LOGIN_ERROR_MSG);
		
		loginService= new LoginService();
		String userName="";
		String password= "";
		String role="Developer";
		
		loginService.loginUser(userName, password,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenUsernameIsOverTheCharacterLimitForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LARGE_USERNAME_MSG);
		
		loginService= new LoginService();
		
		String userName="TestUser123456789Admin";
		String role="Admin";
		
		loginService.checkUserName(userName,role);
		
	}
	@Test
	public void shouldNotLoginWhenUsernameIsOverTheCharacterLimitForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LARGE_USERNAME_MSG);
		
		loginService= new LoginService();
		
		String userName="TestUser123456789Admin";
		String role="Developer";
		
		loginService.checkUserName(userName,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenPasswordIsOverTheCharacterLimitForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LARGE_PASSWORD_MSG);
		
		loginService= new LoginService();
		
		String password= "0123456789@#$AA012345789";
		String role="Admin";
		
		loginService.checkPassword(password,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenPasswordIsOverTheCharacterLimitForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LARGE_PASSWORD_MSG);
		
		loginService= new LoginService();
		
		String password= "123456789@#$AA12345789";
		String role="Developer";
		
		loginService.checkPassword(password,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenUsernamePasswordAreOverTheCharacterLimitForAdminUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LARGE_PASSWORD_MSG);
		
		loginService= new LoginService();
		String userName="TestUser12356789Admin";
		String password= "123456789@#$AA12345789";
		String role="Admin";
		
		loginService.checkPassword(password,role);
		
	}
	
	@Test
	public void shouldNotLoginWhenUsernamePasswordAreOverTheCharacterLimitForDeveloperUser() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.LARGE_PASSWORD_MSG);
		
		loginService= new LoginService();
		String userName="TestUser123456789Admin";
		String password= "123456789@#$AA12345789";
		String role="Developer";
		
		loginService.checkPassword(password,role);
		
	}
	
	@Test
	public void shouldLogoutSuccessfullyForAdminUserWhenLoggedIn () {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.USER_LOGGEDOUT_MSG);
		loginService= new LoginService();
		User usr= new User();
		loginService.logout(usr);
		
	}
	@Test
	public void shouldLogoutSuccessfullyForDeveloperUSerWhenLoggedIn() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.USER_LOGGEDOUT_MSG);
		loginService= new LoginService();
		User usr= new User();
		loginService.logout(usr);
		
	}
	
	
	@Test
	public void shouldThrowExceptionForAdminUserWhenNotLoggedIn () {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.USER_NOT_LOGGEDIN_MSG);
		loginService= new LoginService();
		User usr= new User();
		loginService.checkUserLoggedIn(usr);
		
	}
	@Test
	public void shouldThrowExceptionForDeveloperUserWhenNotLoggedIn() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.USER_NOT_LOGGEDIN_MSG);
		loginService= new LoginService();
		User usr= new User();
		loginService.checkUserLoggedIn(usr);
		
	}
	
//	/////
//	@Test
//	public void shouldNotAllowAdminUserToUseFunctionalitiesAfterLoggedOut () {
//		
//		expected.expect(RuntimeException.class);
//		expected.expectMessage(ApplicationConstants.USER_LOGGEDIN_MSG);
//		
//		loginService= new LoginService();
//		User usr= new User();
//		loginService.logout(usr);
//		loginService.checkuserAddsPostToCluser();
//		
//		
//	}
//	@Test
//	public void shouldNotAllowDeveloperUserToUseFunctionalitiesAfterLoggedOut() {
//		
//		expected.expect(RuntimeException.class);
//		expected.expectMessage(ApplicationConstants.USER_LOGGEDIN_MSG);
//		
//		loginService= new LoginService();
//		User usr= new User();
//		loginService.logout(usr);
//		
//	}
//	
	
	
	@Test
	public void shouldAllowAdminUserToLogInAfterLogout () {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.USER_LOGGEDIN_MSG);
		
		loginService= new LoginService();
		User usr= new User();
		loginService.logIN(usr);
		
	}
	@Test
	public void shouldAllowDeveloperUserToLogInAfterLogout() {
		
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.USER_LOGGEDIN_MSG);
		
		loginService= new LoginService();
		User usr= new User();
		loginService.logIN(usr);
		
	}	
}