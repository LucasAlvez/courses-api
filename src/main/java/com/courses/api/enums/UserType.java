package com.courses.api.enums;

public enum UserType {
	
	STUDENT("Estudante"),
	OWNER("Proprietário");
	
	private String type;
	
	
	private UserType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}	
