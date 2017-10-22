package com.innohub.innosorter.authentication;

import static org.mockito.MockitoAnnotations.initMocks;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Post;
import com.innohub.innosorter.entity.User;
import com.innohub.innosorter.management.IssueManager;
import com.innohub.innosorter.repo.IssueRepositoryService;
import com.innohub.innosorter.util.ApplicationConstants;

public class LoginService {
	
//	@InjectMocks
//    private IssueManager issueManager;
//
//    @Mock
//    private IssueRepositoryService mockIssueRepositoryService;
    
    
	RegistrationServiceDao registrationServiceDao=new RegistrationServiceDao();

	public void loginUser(String username,String password,String role) {
		
		
		boolean isunamepwdCurrect = registrationServiceDao.validateUserNamePassword(username,password,role);
		
	}
	
	public void checkUserName(String username,String role) {
		
		UserNamePasswardVerificationService userNamePasswardVerificationService=new UserNamePasswardVerificationService();
		userNamePasswardVerificationService.checkUsernameLength(username);
		
		
	}
	public void checkPassword(String password,String role) {
		
		UserNamePasswardVerificationService userNamePasswardVerificationService=new UserNamePasswardVerificationService();
		userNamePasswardVerificationService.checkPasswordLength(password);
		
	}
	
	public void checkUserLoggedIn(User user) {
		throw new RuntimeException(ApplicationConstants.USER_NOT_LOGGEDIN_MSG);
		
	}

	public void loguot(User usr) {
		throw new RuntimeException(ApplicationConstants.USER_LOGGEDOUT_MSG);

	}
	
	public void logIN(User usr) {
		throw new RuntimeException(ApplicationConstants.USER_LOGGEDIN_MSG);

	}

	

}
