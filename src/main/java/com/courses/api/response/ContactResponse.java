package com.courses.api.response;

import com.courses.api.entity.ContactEntity;

public class ContactResponse {
	
	private String phoneNumber;
	
	private String cellPhoneNumber;
	
	private String skype;
	
	private String twitter;
	
	private String facebook;
	
	public static ContactResponse build(ContactEntity contact) {
		ContactResponse response = new ContactResponse();
		if (contact != null) {
			response.setCellPhoneNumber(contact.getCellPhoneNumber());
			response.setFacebook(contact.getFacebook());
			response.setPhoneNumber(contact.getPhoneNumber());
			response.setSkype(contact.getSkype());
			response.setTwitter(contact.getTwitter());
		}
		return response;
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