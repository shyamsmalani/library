package com.library.services;

import java.util.List;

import com.library.domain.BookDetails;
import com.library.exception.LibraryException;
import com.library.repository.BookDetailsMapper;

public class BookService implements BookServiceInf {
	
	BookDetailsMapper bookDetailsMapper = new BookDetailsMapper();

	@Override
	public void registerNewBook(String title, String type, String isbn, String author, String desc)
			throws LibraryException {
		bookDetailsMapper.createBook(title, type, isbn, author, desc);
	}

	@Override
	public void deletBook(String[] id) throws LibraryException {
		bookDetailsMapper.deletBook(id);

	}

	@Override
	public List<BookDetails> searchBooks(String searchString, String bookType, String searchBy, Boolean onlyAvail,
			Boolean sameWithType) throws LibraryException {
		return bookDetailsMapper.searchBooks(searchString, bookType, searchBy,onlyAvail, sameWithType);
	}	

}
