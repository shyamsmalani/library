package com.library.domain;

import java.sql.Timestamp;

public class LoginDetails {
	
	private Integer member_Id;
	private String loginId;
	private String password;
	private Boolean activeStatus;
	private String loginType;
	private Timestamp lastLogin;
	
	public Integer getMember_Id() {
		return member_Id;
	}
	public void setMember_Id(Integer member_Id) {
		this.member_Id = member_Id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(Boolean activeStatus) {
		this.activeStatus = activeStatus;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public Timestamp getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	

}
