package com.library.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.library.common.LibraryUtills;
import com.library.domain.MembarDetails;
import com.library.exception.LibraryException;
import com.library.repository.finder.MemberDetailsFinderInf;
import com.library.repository.provider.MemberDetailsProviderInf;

public class MemberDetailsMapper implements MemberDetailsFinderInf, MemberDetailsProviderInf {
	private Connection con = null;
	Logger logger = Logger.getLogger("MemberDetailsMapper");

	@Override
	public MembarDetails createMember(String fname, String lname, String phoneN, String emailId, String uniqueId,
			String idType, String membertype) throws LibraryException {

		String sql = "INSERT INTO member_details (first_name, last_name, phone_num, email_id, address, unique_id, id_type, member_type) "
				+ "VALUES ( '" + fname + "', '" + lname + "', '" + phoneN + "', '" + emailId + "', 'NA', '" + uniqueId
				+ "', '" + idType + "', '" + membertype + "' )";
		System.out.println(sql);
		try {
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " Registering  MembarDetails record in MemberDetailsMapper.createMember.");
			Statement stmt = con.createStatement();
			stmt.execute(sql);

			String selectSql = "SELECT max(member_id) as member_id FROM member_details ";
			ResultSet rs = stmt.executeQuery(selectSql);

			MembarDetails details = new MembarDetails();
			while (rs.next()) {
				details.setMember_Id(rs.getInt("member_id"));
				details.setFirstName(fname);
				details.setLastName(lname);
				details.setPhoneNum(phoneN);
				details.setEmailId(emailId);
				details.setUniqueId(uniqueId);
				details.setIdType(idType);
				details.setMemberType(membertype);
			}

			rs.close();
			stmt.close();
			con.close();
			return details;
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In LoginDetailsMapper.getLoginUser.", e);
			throw new LibraryException("Error while Registration.");
		}
	}

	@Override
	public boolean deleteMember(String membeId) throws LibraryException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateMember(MembarDetails details) throws LibraryException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MembarDetails getMember(String membeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MembarDetails> getInactiveMemberList() throws LibraryException {
		try {
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " Fetching Inactive Member record in MemberDetailsMapper.getInactiveMemberList.");
			Statement stmt = con.createStatement();
			String sql = "SELECT md.member_id as member_id, md.member_type as member_type, md.first_name first_name, md.last_name last_name, md.email_id email_id "
					+ "FROM library.login_details ld  "
					+ "LEFT JOIN  library.member_details md "
					+ "ON ld.member_id=md.member_id "
					+ "WHERE ld.active_falg = 'false'";
			ResultSet rs = stmt.executeQuery(sql);
			List<MembarDetails> detailList = new ArrayList<>();
			MembarDetails details = null;
			while (rs.next()) {
				details = new MembarDetails();
				details.setMember_Id(rs.getInt("member_id"));
				details.setMemberType(rs.getString("member_type"));
				details.setFirstName(rs.getString("first_name"));
				details.setLastName(rs.getString("last_name"));
				details.setEmailId(rs.getString("email_id"));
				detailList.add(details);
			}

			rs.close();
			stmt.close();
			con.close();
			return detailList;
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In MemberDetailsMapper.getInactiveMemberList.", e);
			throw new LibraryException("Error while getting Inactive members..");
		}		
	}

	@Override
	public void deleteMember(String[] memberId) throws LibraryException {
		try {
			con = new DatabaseConnection().getConnection();
			logger.log(Level.INFO, " Creating LoginDetails record in MemberDetailsMapper.deleteMember.");
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM library.member_details WHERE member_id in("
					+ LibraryUtills.getArrayToString(memberId) + ")";
			System.out.println(sql);
			stmt.execute(sql);
			stmt.close();
			con.close();
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception In MemberDetailsMapper.deleteMember.", e);
			throw new LibraryException("Error while Deleting User.");
		}
		
	}

}
