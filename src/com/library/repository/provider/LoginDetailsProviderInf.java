package com.library.repository.provider;

import com.library.exception.LibraryException;

public interface LoginDetailsProviderInf {
	
	public void createUser(String loginId, String password, String membertype, Integer memberId, String activFlag) throws LibraryException;

}
