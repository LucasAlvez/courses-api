package com.courses.api.request;

import javax.validation.constraints.NotBlank;
import static com.courses.api.validations.BeanValidation.REQUIRED_NAME;

public class CategoryRequest {
	
	@NotBlank(message = REQUIRED_NAME)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
