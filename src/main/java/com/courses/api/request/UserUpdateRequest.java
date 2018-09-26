package com.courses.api.request;

import static com.courses.api.validations.BeanValidation.REQUIRED_EMAIL;
import static com.courses.api.validations.BeanValidation.REQUIRED_NAME;
import static com.courses.api.validations.BeanValidation.REQUIRED_PASS;
import static com.courses.api.validations.BeanValidation.REQUIRED_ROLES;
import static com.courses.api.validations.BeanValidation.REQUIRED_TYPE;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserUpdateRequest {

	@NotBlank(message = REQUIRED_NAME)
	private String name;

	@NotBlank(message = REQUIRED_EMAIL)
	private String email;
	
	@NotBlank(message = REQUIRED_TYPE)
	private String type;

	@NotNull(message = REQUIRED_PASS)
	private String pass;
	
	@NotEmpty(message = REQUIRED_ROLES)
	private List<Long> roles;

	
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
	
	public List<Long> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Long> roles) {
		this.roles = roles;
	}
}
