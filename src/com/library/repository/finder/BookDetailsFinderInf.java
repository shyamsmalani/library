package com.library.repository.finder;

import java.util.List;

import com.library.domain.BookDetails;
import com.library.exception.LibraryException;

public interface BookDetailsFinderInf {
		
	public List<BookDetails> searchBooks(String searchString, String bookType, 
			String searchBy,
			Boolean onlyAvail,
			Boolean sameWithType) throws LibraryException;
	public void lockBooks(String[] ids) throws LibraryException;
	
	public void unlockBooks(String[] ids) throws LibraryException;

}
