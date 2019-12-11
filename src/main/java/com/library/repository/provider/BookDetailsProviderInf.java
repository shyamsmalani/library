package com.library.repository.provider;

import com.library.exception.LibraryException;

public interface BookDetailsProviderInf {
	
	public void createBook(String title, String type, 
			String isbn, 
			String author, 
			String desc) throws LibraryException; 
	
	public void deletBook(String[] id) throws LibraryException; 

}
