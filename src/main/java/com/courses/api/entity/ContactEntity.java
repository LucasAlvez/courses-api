package com.courses.api.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ContactEntity implements Serializable {
		
	private static final long serialVersionUID = 1L;

	private String phoneNumber;
	
	private String cellPhoneNumber;
	
	private String skype;
	
	private String twitter;
	
	private String facebook;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}

	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
}