package com.library.domain;

import java.sql.Timestamp;
import java.util.List;

public class MembarDetails {
	
	private Integer member_Id;
	private String firstName;
	private String lastName;
	private String phoneNum;
	private String emailId;
	private String address;
	private String uniqueId;
	private String IdType;
	private String memberType;
	private Timestamp lastUpdate;
	private List<BookDetails> issuedBooks;
	
	public Integer getMember_Id() {
		return member_Id;
	}
	public void setMember_Id(Integer member_Id) {
		this.member_Id = member_Id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getIdType() {
		return IdType;
	}
	public void setIdType(String idType) {
		IdType = idType;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public Timestamp getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public List<BookDetails> getIssuedBooks() {
		return issuedBooks;
	}
	public void setIssuedBooks(List<BookDetails> issuedBooks) {
		this.issuedBooks = issuedBooks;
	}
}
