package com.courses.api.request;

import static com.courses.api.validations.BeanValidation.REQUIRED_NAME;

import javax.validation.constraints.NotBlank;

public class StudentUpdateRequest {

	@NotBlank(message = REQUIRED_NAME)
	private String name;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
