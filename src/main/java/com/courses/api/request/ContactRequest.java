package com.courses.api.request;

import com.courses.api.entity.ContactEntity;

public class ContactRequest {
	
	private String phoneNumber;
	
	private String cellPhoneNumber;
	
	private String skype;
	
	private String twitter;
	
	private String facebook;

	public static ContactEntity build(ContactRequest request) {
		ContactEntity entity = new ContactEntity();
		entity.setCellPhoneNumber(request.getCellPhoneNumber());
		entity.setFacebook(request.getFacebook());
		entity.setPhoneNumber(request.getPhoneNumber());
		entity.setSkype(request.getSkype());
		entity.setTwitter(request.getTwitter());
		return entity;
	}

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
