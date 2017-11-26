package com.library.domain;

public class LibraryStore {
	
	private Integer issue_Id;
	private Integer book_id;
	private Integer member_id;
	private String issuedBy;
	private String issue_date;
	private String return_date;
	private String last_date_to_return;
	private Boolean returned_flag;
	private Boolean lock_flag;
	private String isbn;
	private String booktitle;
	//private Integer dueAmt;
	
	public Integer getIssue_Id() {
		return issue_Id;
	}
	public void setIssue_Id(Integer issue_Id) {
		this.issue_Id = issue_Id;
	}
	public Integer getBook_id() {
		return book_id;
	}
	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public String getIssuedBy() {
		return issuedBy;
	}
	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}
	
	public String getIssue_date() {
		return issue_date;
	}
	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}
	public String getReturn_date() {
		return return_date;
	}
	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}
	public String getLast_date_to_return() {
		return last_date_to_return;
	}
	public void setLast_date_to_return(String last_date_to_return) {
		this.last_date_to_return = last_date_to_return;
	}
	public Boolean getReturned_flag() {
		return returned_flag;
	}
	public void setReturned_flag(Boolean returned_flag) {
		this.returned_flag = returned_flag;
	}
	
	public Boolean getLock_flag() {
		return lock_flag;
	}
	public void setLock_flag(Boolean lock_flag) {
		this.lock_flag = lock_flag;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}	
	
}
