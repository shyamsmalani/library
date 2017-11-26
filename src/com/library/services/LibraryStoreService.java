package com.library.services;

import java.util.List;

import com.library.domain.LibraryStore;
import com.library.exception.LibraryException;
import com.library.repository.BookDetailsMapper;
import com.library.repository.LibraryStoreMapper;

public class LibraryStoreService implements LibraryStoreServiceInf {

	private LibraryStoreMapper libraryStoreMapper = new LibraryStoreMapper();
	private BookDetailsMapper bookDetailsMapper = new BookDetailsMapper();

	@Override
	public void lockBook(String[] bookId, String UserId) throws LibraryException {
		bookDetailsMapper.lockBooks(bookId);
		libraryStoreMapper.lockBook(bookId, UserId);

	}

	@Override
	public List<LibraryStore> getLockBooks() throws LibraryException {
		return libraryStoreMapper.getLockBooks();
	}

	@Override
	public List<LibraryStore> getUserBooks(String loginId) throws LibraryException {
		if(loginId != null && !loginId.trim().equals("")){
			return libraryStoreMapper.getUserBooks(loginId);
		}else{
			throw new LibraryException("User name not provided!!");
		}
	}

	@Override
	public void manageLibrary(String[] issueId, String[] deleteId) throws LibraryException {
		if (issueId != null && issueId.length > 0) {
			libraryStoreMapper.issueBook(issueId);
		}
		if (deleteId != null && deleteId.length > 0) {
			libraryStoreMapper.deleteRequest(deleteId);
			bookDetailsMapper.unlockBooks(deleteId);
		}

	}

	@Override
	public void returnBooks(String[] returnId) throws LibraryException {
		if (returnId != null && returnId.length > 0) {
			libraryStoreMapper.returnBooks(returnId);
			bookDetailsMapper.unlockBooks(returnId);
		}
		
	}

}
