package com.innohub.innosorter.authentication;

import com.innohub.innosorter.entity.User;
import com.innohub.innosorter.util.ApplicationConstants;

public class LoginService {

    RegistrationServiceDao registrationServiceDao = new RegistrationServiceDao();

    public void loginUser(String username, String password, String role) {

        if (!registrationServiceDao.validateUserNamePassword(username, password, role)) {

            throw new RuntimeException(ApplicationConstants.LOGIN_ERROR_MSG);
        }
    }

    public void checkUserName(String username, String role) {

        UserNamePasswardVerificationService userNamePasswardVerificationService = new UserNamePasswardVerificationService();
        userNamePasswardVerificationService.checkUsernameLength(username);

    }

    public void checkPassword(String password, String role) {

        UserNamePasswardVerificationService userNamePasswardVerificationService = new UserNamePasswardVerificationService();
        userNamePasswardVerificationService.checkPasswordLength(password);

    }

    public void checkUserLoggedIn(User user) {
        throw new RuntimeException(ApplicationConstants.USER_NOT_LOGGEDIN_MSG);

    }

    public void logout(User usr) {
        throw new RuntimeException(ApplicationConstants.USER_LOGGEDOUT_MSG);

    }

    public void logIN(User usr) {
        throw new RuntimeException(ApplicationConstants.USER_LOGGEDIN_MSG);

    }

}
