package com.library.services;

import java.util.List;

import com.library.domain.LibraryStore;
import com.library.exception.LibraryException;

public interface LibraryStoreServiceInf {
	
	public void lockBook(String[] bookId, String UserId) throws LibraryException;
	
	public List<LibraryStore> getLockBooks() throws LibraryException;
	
	public List<LibraryStore> getUserBooks(String loginId) throws LibraryException;
	
	public void manageLibrary(String[] issueId, String[] deleteId) throws LibraryException;
	
	public void returnBooks(String[] returnId) throws LibraryException;

}
