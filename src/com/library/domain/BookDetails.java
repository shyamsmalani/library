package com.library.domain;

import java.sql.Timestamp;

public class BookDetails {
	
	private Integer book_Id;
	private String booktitle;
	private Integer isbn;
	private String descrition;
	private String author;
	private String subjectType;
	private Timestamp entryDate;
	private Boolean availabilityStatus;
	//private Date deleteDate;
	//private Date lastUpdated;
	//private String lastUpdatedBy;
	public Integer getBook_Id() {
		return book_Id;
	}
	public void setBook_Id(Integer book_Id) {
		this.book_Id = book_Id;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public Integer getIsbn() {
		return isbn;
	}
	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}
	public String getDescrition() {
		return descrition;
	}
	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	public Timestamp getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Timestamp entryDate) {
		this.entryDate = entryDate;
	}
	public Boolean getAvailabilityStatus() {
		return availabilityStatus;
	}
	public void setAvailabilityStatus(Boolean availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}
	
}
