package com.library.repository.finder;

import java.util.List;

import com.library.domain.MembarDetails;
import com.library.exception.LibraryException;

public interface MemberDetailsFinderInf {
	
	public MembarDetails getMember(String membeId);
	
	public List<MembarDetails> getInactiveMemberList() throws LibraryException;
	
	public void deleteMember(String[] memberId) throws LibraryException;

}
