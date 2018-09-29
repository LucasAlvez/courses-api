package com.courses.api.response;

public class StudentResponse {

	private Long id;

	private String email;

	private String roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String string) {
		this.roles = string;
	}
}
