package com.courses.api.request;

import static com.courses.api.validations.BeanValidation.REQUIRED_EMAIL;
import static com.courses.api.validations.BeanValidation.REQUIRED_PASS;

import javax.validation.constraints.NotBlank;

public class CredentialsRequest {

	@NotBlank(message = REQUIRED_EMAIL)
	private String email;

	@NotBlank(message = REQUIRED_PASS)
	private String pass;

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
}
