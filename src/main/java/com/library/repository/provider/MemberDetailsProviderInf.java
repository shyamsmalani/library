package com.library.repository.provider;

import com.library.domain.MembarDetails;
import com.library.exception.LibraryException;

public interface MemberDetailsProviderInf {
	
	public MembarDetails createMember(String fname, String lname, String phoneN, String emailId,
			String uniqueId, String idType, String membertype) throws LibraryException;
	
	public boolean deleteMember(String membeId) throws LibraryException;
	
	public boolean updateMember(MembarDetails details) throws LibraryException;

}
