package com.courses.api.enums;

public enum Profile {
	USER("ROLE_USER"), 
	ADMIN("ROLE_ADMIN");

	private String profile;

	private Profile(String profile) {
		this.profile = profile;
	}

	public String getProfile() {
		return profile;
	}
}
