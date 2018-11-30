package com.courses.api.request;

import static com.courses.api.validations.BeanValidation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AccountRequest {
	
	@NotBlank(message = REQUIRED_NAME)
	private String name;
	
	@NotBlank(message = REQUIRED_EMAIL)
	private String email;

	@NotBlank(message = REQUIRED_PASS)
	private String pass;

	@NotNull(message = REQUIRED_ADDRESS)
	@Valid
	private AddressRequest address;
	
	@NotNull(message = REQUIRED_CONTACT)
	@Valid
	private ContactRequest contact;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public AddressRequest getAddress() {
		return address;
	}

	public void setAddress(AddressRequest address) {
		this.address = address;
	}

	public ContactRequest getContact() {
		return contact;
	}

	public void setContact(ContactRequest contact) {
		this.contact = contact;
	}
}
