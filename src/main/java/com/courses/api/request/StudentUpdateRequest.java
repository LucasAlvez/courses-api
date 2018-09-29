package com.courses.api.request;

import static com.courses.api.validations.BeanValidation.REQUIRED_EMAIL;
import static com.courses.api.validations.BeanValidation.REQUIRED_PASS;
import static com.courses.api.validations.BeanValidation.REQUIRED_ROLES;
import static com.courses.api.validations.BeanValidation.REQUIRED_TYPE;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.courses.api.enums.Roles;

public class StudentUpdateRequest {

	@NotBlank(message = REQUIRED_EMAIL)
	private String email;

	@NotBlank(message = REQUIRED_TYPE)
	private String type;

	@NotNull(message = REQUIRED_PASS)
	private String pass;

	@NotEmpty(message = REQUIRED_ROLES)
	private Roles roles;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Roles getRoles() {
		return roles;
	}
	
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
}
