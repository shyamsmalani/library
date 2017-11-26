package com.library.services;

import java.util.List;

import com.library.domain.MembarDetails;
import com.library.exception.LibraryException;

public interface MemberServiceInf {
	
	public boolean registerNewMember(String loginId, String password, 
			String fname, 
			String lname, 
			String phoneN, 
			String emailId, 
			String uniqueId, 
			String idType, 
			String membertype) throws LibraryException;
	
	public void deleteMember(String[] id) throws LibraryException;
	
	public MembarDetails getMember(String loginId) throws LibraryException;
	
	public void inactiveMember(String[] id) throws LibraryException;
	
	public void activateMember(String[] id) throws LibraryException;
		
	public List<MembarDetails> getInactivateMemberList() throws LibraryException;
	

}

