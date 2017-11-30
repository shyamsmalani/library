package com.library.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.library.common.LibraryUtills;
import com.library.domain.LoginDetails;
import com.library.exception.LibraryException;
import com.library.repository.finder.LoginDetailsFinderInf;
import com.library.repository.provider.LoginDetailsProviderInf;

public class LoginDetailsMapper implements LoginDetailsFinderInf, LoginDetailsProviderInf {

	private Connection con = null;
	Logger logger = Logger.getLogger("LoginDetailsMapper");

	@Override
	public void createUser(String loginId, String password, String membertype, Integer memberId, String activFlag)
			throws LibraryException {
		try {
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " Creating LoginDetails record in LoginDetailsMapper.createUser.");
			Statement stmt = con.createStatement();
			String sql = "INSERT INTO login_details (member_id, loginId, lpassword, active_falg, login_type) "
					+ "VALUES ( '" + memberId + "', '" + loginId + "', '" + password + "', '"+activFlag+"', '" + membertype
					+ "' )";
			System.out.println(sql);
			stmt.execute(sql);
			stmt.close();
			con.close();
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In LoginDetailsMapper.getLoginUser.", e);
			throw new LibraryException("Error while Registration.");
		}
	}

	@Override
	public LoginDetails getLoginUser(String loginId, String password, String activFlag) throws LibraryException {

		try {
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " Fetching User record in LoginDetailsMapper.getLoginUser.");
			Statement stmt = con.createStatement();
			String sql = "SELECT member_id, loginId, lpassword, active_falg, login_type  " + "FROM LOGIN_DETAILS "
					+ "WHERE loginId='" + loginId + "' " + "AND lpassword ='" + password + "' " + "AND active_falg ='"
					+ activFlag + "'";
			ResultSet rs = stmt.executeQuery(sql);
			LoginDetails details = null;
			while (rs.next()) {
				details = new LoginDetails();
				details.setMember_Id(rs.getInt("member_id"));
				details.setLoginId(rs.getString("loginId"));
				details.setPassword(rs.getString("lpassword"));
				details.setActiveStatus(rs.getBoolean("active_falg"));
				details.setLoginType(rs.getString("login_type"));
			}

			rs.close();
			stmt.close();
			con.close();
			return details;
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In LoginDetailsMapper.getLoginUser.", e);
			throw new LibraryException("Error while login.");
		}
	}

	@Override
	public Boolean userExist(String loginId) throws LibraryException {
		try {
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " Fetching User record in LoginDetailsMapper.getLoginUser.");
			Statement stmt = con.createStatement();
			String sql = "SELECT member_id, loginId, lpassword, active_falg, login_type  " + "FROM LOGIN_DETAILS "
					+ "WHERE loginId='" + loginId + "' " + "AND active_falg ='true'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				rs.close();
				stmt.close();
				con.close();
				return true;
			} else {
				rs.close();
				stmt.close();
				con.close();
				return false;
			}

		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In LoginDetailsMapper.getLoginUser.", e);
			throw new LibraryException("Error while checking existing user..");
		}
	}

	@Override
	public void deleteUser(String[] memberid) throws LibraryException {
		try {
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " Creating LoginDetails record in LoginDetailsMapper.deleteUser.");
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM library.login_details WHERE member_id in("
					+ LibraryUtills.getArrayToString(memberid) + ")";
			System.out.println(sql);
			stmt.execute(sql);
			stmt.close();
			con.close();
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In LoginDetailsMapper.deleteUser.", e);
			throw new LibraryException("Error while Deleting Users.");
		}
	}

	@Override
	public void activateUser(String[] memberid) throws LibraryException {
		try {
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " Creating LoginDetails record in LoginDetailsMapper.activateUser.");
			Statement stmt = con.createStatement();
			String sql = "UPDATE library.login_details SET active_falg='true'  WHERE member_id in("
					+ LibraryUtills.getArrayToString(memberid) + ")";
			System.out.println(sql);
			stmt.execute(sql);
			stmt.close();
			con.close();
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In LoginDetailsMapper.activateUser.", e);
			throw new LibraryException("Error while Activating Users.");
		}
	}

	@Override
	public void deActivateUser(String[] memberid) throws LibraryException {
		try {
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " Creating LoginDetails record in LoginDetailsMapper.deActivateUser.");
			Statement stmt = con.createStatement();
			String sql = "UPDATE library.login_details SET active_falg='false'  WHERE member_id in("
					+ LibraryUtills.getArrayToString(memberid) + ")";
			System.out.println(sql);
			stmt.execute(sql);
			stmt.close();
			con.close();
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In LoginDetailsMapper.deActivateUser.", e);
			throw new LibraryException("Error while DeActivating Users.");
		}
	}

}
