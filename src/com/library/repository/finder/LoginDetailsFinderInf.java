package com.library.repository.finder;

import com.library.domain.LoginDetails;
import com.library.exception.LibraryException;

public interface LoginDetailsFinderInf {
	
	public LoginDetails getLoginUser(String loginId, String password, String activFlag) throws LibraryException;
	public Boolean userExist(String memberid) throws LibraryException;
	public void deleteUser(String[] memberid) throws LibraryException;
	public void activateUser(String[] memberid) throws LibraryException;
	public void deActivateUser(String[] memberid) throws LibraryException;
}
