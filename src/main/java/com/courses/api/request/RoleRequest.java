package com.courses.api.request;

import static com.courses.api.validations.BeanValidation.REQUIRED_ROLE_ID;

import javax.validation.constraints.NotNull;

public class RoleRequest {

	@NotNull(message = REQUIRED_ROLE_ID)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
