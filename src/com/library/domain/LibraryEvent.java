package com.library.domain;

import java.sql.Timestamp;

public class LibraryEvent {
	private Integer event_Id;
	private Timestamp start_date;
	private Timestamp end_date;
	private String event_name;
	private String event_details;
	private String contacts;
	public Integer getEvent_Id() {
		return event_Id;
	}
	public void setEvent_Id(Integer event_Id) {
		this.event_Id = event_Id;
	}
	public Timestamp getStart_date() {
		return start_date;
	}
	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}
	public Timestamp getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getEvent_details() {
		return event_details;
	}
	public void setEvent_details(String event_details) {
		this.event_details = event_details;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}	

}
