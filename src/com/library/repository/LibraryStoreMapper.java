package com.library.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.library.common.LibraryUtills;
import com.library.domain.LibraryStore;
import com.library.exception.LibraryException;
import com.library.repository.finder.LibraryStoreFinderInf;
import com.library.repository.provider.LibraryStoreProviderInf;

public class LibraryStoreMapper implements LibraryStoreFinderInf, LibraryStoreProviderInf {

	private Connection con = null;
	Logger logger = Logger.getLogger("LibraryStoreMapper");

	@Override
	public void lockBook(String[] bookId, String UserId) throws LibraryException {

		try {
			con = new DatabaseConnection().getConnection();
			for (String id : bookId) {
				String sql = "INSERT INTO library_store (book_id, member_id, lock_flag, issuer_id) " + "VALUES ( " + id
						+ ", (SELECT member_id FROM login_details where loginId = '" + UserId + "'), TRUE , '" + UserId
						+ "' )";
				System.out.println(sql);
				logger.log(Level.INFO, " Creating Library Store Lock record in LibraryStoreMapper.lockBook.");
				Statement stmt = con.createStatement();
				stmt.execute(sql);
				stmt.close();
			}

			con.close();
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In LibraryStoreMapper.lockBook.", e);
			throw new LibraryException("Error while Locking Book.");
		}

	}

	@Override
	public List<LibraryStore> getLockBooks() throws LibraryException {
		try {
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " Fetching LockBooks record in LibraryStoreMapper.getLockBooks.");
			Statement stmt = con.createStatement();
			String sql = "SELECT book_id, (SELECT isbn FROM library.book_details ld where ld.book_id=ls.book_id) isbn, ls.issuer_id FROM library.library_store ls WHERE ls.lock_flag IS true";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			List<LibraryStore> detailList = new ArrayList<>();
			LibraryStore details = null;
			while (rs.next()) {
				details = new LibraryStore();
				details.setBook_id(rs.getInt("book_id"));
				details.setIsbn(rs.getString("isbn"));
				details.setIssuedBy(rs.getString("issuer_id"));
				detailList.add(details);
			}

			rs.close();
			stmt.close();
			con.close();
			return detailList;
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In LibraryStoreMapper.getLockBooks.", e);
			throw new LibraryException(e.getMessage());
		}
	}

	@Override
	public List<LibraryStore> getUserBooks(String loginId) throws LibraryException {
		try {
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " Fetching Books record for User in LibraryStoreMapper.getUserBooks.");
			Statement stmt = con.createStatement();
			String sql = "SELECT ls.issueId, ls.book_id, ls.member_id, (SELECT booktitle FROM library.book_details ld where ld.book_id=ls.book_id) booktitle,"
					+ " ls.issuer_id, ls.issue_date, ls.return_date, ls.last_date, ls.returned_flag, ls.lock_flag "
					+ "FROM library.library_store ls " + "WHERE ls.issuer_id = '" + loginId + "' "
					+ "	AND ls.returned_flag is not true ";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			List<LibraryStore> detailList = new ArrayList<>();
			LibraryStore details = null;
			while (rs.next()) {
				details = new LibraryStore();
				details.setIssue_Id(rs.getInt("issueId"));
				details.setBooktitle(rs.getString("booktitle"));
				details.setIssuedBy(rs.getString("issuer_id"));
				details.setIssue_date(LibraryUtills.getDateInFormate(rs.getTimestamp("issue_date")));
				details.setBook_id(rs.getInt("book_id"));
				details.setMember_id(rs.getInt("member_id"));
				details.setReturn_date(LibraryUtills.getDateInFormate(rs.getTimestamp("return_date")));
				details.setLast_date_to_return(LibraryUtills.getDateInFormate(rs.getTimestamp("last_date")));
				details.setReturned_flag(rs.getBoolean("returned_flag"));
				details.setLock_flag(rs.getBoolean("lock_flag"));
				detailList.add(details);
			}

			rs.close();
			stmt.close();
			con.close();
			return detailList;
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In LibraryStoreMapper.getUserBooks.", e);
			throw new LibraryException("Error while getting User Books.");
		}
	}

	@Override
	public void issueBook(String[] issueId) throws LibraryException {
		try {
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " Updating LibraryStore record in LibraryStoreMapper.issueBook.");
			Statement stmt = con.createStatement();
			String sql = "UPDATE library.library_store ls SET ls.issue_date = now(), ls.last_date = (NOW() + interval 15 DAY), ls.returned_flag = FALSE, ls.lock_flag=FALSE  WHERE ls.book_id in("
					+ LibraryUtills.getArrayToString(issueId) + ")";
			System.out.println(sql);
			stmt.execute(sql);
			stmt.close();
			con.close();
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In LibraryStore.issueBook.", e);
			throw new LibraryException("Error while Updating LibraryStore.");
		}

	}

	@Override
	public void deleteRequest(String[] deleteId) throws LibraryException {
		try {
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " DELETING LibraryStore record in LibraryStoreMapper.deleteRequest.");
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM library.library_store WHERE book_id in("
					+ LibraryUtills.getArrayToString(deleteId) + ")";
			System.out.println(sql);
			stmt.execute(sql);
			stmt.close();
			con.close();
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In LibraryStoreMapper.deleteRequest.", e);
			throw new LibraryException("Error while Deleting Library Record.");
		}

	}

	@Override
	public void returnBooks(String[] returnIds) throws LibraryException {
		try {
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " Updating LibraryStore record in LibraryStoreMapper.returnBooks.");
			Statement stmt = con.createStatement();
			String sql = "UPDATE library.library_store ls SET ls.return_date = now(), ls.returned_flag = TRUE  WHERE ls.book_id in("
					+ LibraryUtills.getArrayToString(returnIds) + ")";
			System.out.println(sql);
			stmt.execute(sql);
			stmt.close();
			con.close();
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In LibraryStore.returnBooks.", e);
			throw new LibraryException("Error while Updating LibraryStore.");
		}

	}

}
