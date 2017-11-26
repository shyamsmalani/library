package com.library.repository.finder;

import java.util.List;

import com.library.domain.LibraryStore;
import com.library.exception.LibraryException;

public interface LibraryStoreFinderInf {
	public void lockBook(String[] bookId, String UserId) throws LibraryException;
	public List<LibraryStore> getLockBooks() throws LibraryException;
	public List<LibraryStore> getUserBooks(String loginId) throws LibraryException;
	public void issueBook(String[] issueId) throws LibraryException;
	public void deleteRequest(String[] deleteId) throws LibraryException;
	
	public void returnBooks(String[] returnIds) throws LibraryException;


}
