package com.library.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.library.common.LibraryUtills;
import com.library.domain.BookDetails;
import com.library.exception.LibraryException;
import com.library.repository.finder.BookDetailsFinderInf;
import com.library.repository.provider.BookDetailsProviderInf;

public class BookDetailsMapper implements BookDetailsFinderInf, BookDetailsProviderInf {

	private Connection con = null;
	Logger logger = Logger.getLogger("BookDetailsMapper");

	@Override
	public void createBook(String title, String type, String isbn, String author, String desc) throws LibraryException {
		try {
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " Creating LoginDetails record in BookDetailsMapper.createBook.");
			Statement stmt = con.createStatement();
			String sql = "INSERT INTO book_details (booktitle, isbn, discription, author, subject_type, book_availiable ) "
					+ "VALUES ( '" + title + "', '" + isbn + "', '" + desc + "', '" + author + "', '" + type
					+ "', TRUE )";
			System.out.println(sql);
			stmt.execute(sql);
			stmt.close();
			con.close();
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In BookDetailsMapper.createBook.", e);
			throw new LibraryException(e.getMessage());
		}
	}

	@Override
	public void deletBook(String[] id) throws LibraryException {
		try {
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " Delete BookDetails record in BookDetailsMapper.deletBook.");
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM library.book_details WHERE book_id in(" + LibraryUtills.getArrayToString(id)
					+ ")";
			System.out.println(sql);
			stmt.execute(sql);
			stmt.close();
			con.close();
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In BookDetailsMapper.deletBook.", e);
			throw new LibraryException("Error while Deleting Book.");
		}

	}

	@Override
	public List<BookDetails> searchBooks(String searchString, String bookType, String searchBy, Boolean onlyAvail,
			Boolean sameWithType) throws LibraryException {
		try {
			String orAnd = "AND";
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " Fetching Book record in BookDetailsMapper.searchBooks.");
			Statement stmt = con.createStatement();

			StringBuilder sql = new StringBuilder();
			sql.append(
					"SELECT book_id, booktitle, isbn, discription, author, subject_type, book_availiable FROM BOOK_DETAILS WHERE ");
			if (onlyAvail) {
				sql.append(" book_availiable is true AND");
			}
			if (sameWithType) {
				orAnd = "OR";
			}
			if ("ISBN".equals(searchBy)) {
				sql.append(" isbn = " + searchString + " " + orAnd);
			}
			if ("title".equals(searchBy)) {
				sql.append(" booktitle like '%" + searchString + "%' " + orAnd);
			}
			if ("type".equals(searchBy)) {
				sql.append(" subject_type like '%" + bookType + "%' " + orAnd);
			}
			String query = sql.toString();
			query = query.substring(0, query.lastIndexOf(" "));
			ResultSet rs = stmt.executeQuery(query);
			List<BookDetails> list = new ArrayList<>();
			BookDetails details = null;
			while (rs.next()) {
				details = new BookDetails();
				details.setBook_Id(rs.getInt("book_Id"));
				details.setBooktitle(rs.getString("booktitle"));
				details.setAuthor(rs.getString("author"));
				details.setIsbn(rs.getInt("isbn"));
				details.setSubjectType(rs.getString("subject_type"));
				details.setAvailabilityStatus(
						rs.getBoolean("book_availiable"));
				details.setDescrition(rs.getString("discription"));

				list.add(details);
			}

			rs.close();
			stmt.close();
			con.close();
			return list;
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In BookDetailsMapper.searchBooks.", e);
			throw new LibraryException("Error Book Search.");
		}
	}

	@Override
	public void lockBooks(String[] ids) throws LibraryException {
		try {
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " Updating BookDetails record in BookDetailsMapper.lockBooks.");
			Statement stmt = con.createStatement();
			String sql = "UPDATE library.book_details SET book_availiable = FALSE  WHERE book_id in("
					+ LibraryUtills.getArrayToString(ids) + ")";
			System.out.println(sql);
			stmt.execute(sql);
			stmt.close();
			con.close();
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In BookDetailsMapper.lockBooks.", e);
			throw new LibraryException("Error while Updating BookDetails.");
		}
		
	}

	@Override
	public void unlockBooks(String[] ids) throws LibraryException {
		try {
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " Updating BookDetails record in BookDetailsMapper.unlockBooks.");
			Statement stmt = con.createStatement();
			String sql = "UPDATE library.book_details SET book_availiable = TRUE  WHERE book_id in("
					+ LibraryUtills.getArrayToString(ids) + ")";
			System.out.println(sql);
			stmt.execute(sql);
			stmt.close();
			con.close();
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In BookDetailsMapper.unlockBooks.", e);
			throw new LibraryException("Error while Updating BookDetails.");
		}
		
	}

}
