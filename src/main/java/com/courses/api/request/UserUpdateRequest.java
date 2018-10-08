package com.courses.api.request;

import static com.courses.api.validations.BeanValidation.REQUIRED_EMAIL;
import static com.courses.api.validations.BeanValidation.REQUIRED_NAME;
import static com.courses.api.validations.BeanValidation.REQUIRED_PASS;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserUpdateRequest {
	
	@NotBlank(message = REQUIRED_NAME)
	private String name;
	
	@NotBlank(message = REQUIRED_EMAIL)
	private String email;

	@NotNull(message = REQUIRED_PASS)
	private String pass;
	
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
}
