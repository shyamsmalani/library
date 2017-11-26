package com.library.services;

import java.util.List;

import com.library.domain.BookDetails;
import com.library.exception.LibraryException;

public interface BookServiceInf {
	
	public void registerNewBook(String title, String type, 
			String isbn, 
			String author, 
			String desc) throws LibraryException; 
	
	public void deletBook(String[] id) throws LibraryException; 
	
	public List<BookDetails> searchBooks(String searchString, String bookType, 
			String searchBy, 
			Boolean onlyAvail, 
			Boolean sameWithType) throws LibraryException;

}
