package com.courses.api.request;

import javax.validation.constraints.NotBlank;
import static com.courses.api.validations.BeanValidation.REQUIRED_EMAIL;

public class EmailRequest {

	@NotBlank(message = REQUIRED_EMAIL)
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
