package com.library.services;

import java.util.List;

import com.library.domain.LoginDetails;
import com.library.domain.MembarDetails;
import com.library.exception.LibraryException;
import com.library.repository.LoginDetailsMapper;
import com.library.repository.MemberDetailsMapper;

public class MemberService implements MemberServiceInf {

	private MemberDetailsMapper memberDetailMepper = new MemberDetailsMapper();
	private LoginDetailsMapper loginMapper = new LoginDetailsMapper();

	@Override
	public boolean registerNewMember(String loginId, String password, String fname, String lname, String phoneN,
			String emailId, String uniqueId, String idType, String membertype, String activFlag)
			throws LibraryException {
		boolean status = false;
		if (loginMapper.userExist(loginId)) {
			throw new LibraryException("User " + loginId + " already exist.");
		}

		MembarDetails details = memberDetailMepper.createMember(fname, lname, phoneN, emailId, uniqueId, idType,
				membertype);
		if (details == null || details.getMember_Id() == null) {
			return false;
		}
		loginMapper.createUser(loginId, password, membertype, details.getMember_Id(), activFlag);
		LoginDetails loDetails = loginMapper.getLoginUser(loginId, password, activFlag);
		if (loDetails != null) {
			status = true;
		}
		return status;
	}

	@Override
	public void deleteMember(String[] memberId) throws LibraryException {
		if (memberId != null && memberId.length != 0) {
			loginMapper.deleteUser(memberId);
			memberDetailMepper.deleteMember(memberId);
		}
	}

	@Override
	public Boolean memberExist(String memberId) throws LibraryException {
		// TODO Auto-generated method stub
		return loginMapper.userExist(memberId);
	}

	@Override
	public void inactiveMember(String[] memberId) throws LibraryException {
		// TODO Auto-generated method stub
	}

	@Override
	public void activateMember(String[] memberId) throws LibraryException {
		if (memberId != null && memberId.length != 0)
			loginMapper.activateUser(memberId);
	}

	@Override
	public List<MembarDetails> getInactivateMemberList() throws LibraryException {
		return memberDetailMepper.getInactiveMemberList();
	}

}
