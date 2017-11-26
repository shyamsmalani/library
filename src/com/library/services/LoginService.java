package com.library.services;

import com.library.domain.LoginDetails;
import com.library.exception.LibraryException;
import com.library.repository.LoginDetailsMapper;

public class LoginService implements LoginServiceInf {

	private LoginDetailsMapper loginMapper = new LoginDetailsMapper();

	@Override
	public boolean getLogin(String username, String password, String type) throws LibraryException {

		if (username != null && password != null && type != null) {
			LoginDetails loginDetails = loginMapper.getLoginUser(username, password, "true");
			if (loginDetails != null && type.equalsIgnoreCase(loginDetails.getLoginType()))
				return true;
		}
		return false;
	}

	@Override
	public boolean verifyUser(String username, String type) {
		// TODO Auto-generated method stub
		return false;
	}

}
