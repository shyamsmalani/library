package com.library.services;

import com.library.exception.LibraryException;

public interface LoginServiceInf {
	
	public boolean getLogin(String username, String password, String type) throws LibraryException;
	public boolean verifyUser(String username, String type);

}
