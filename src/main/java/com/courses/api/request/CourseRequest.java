package com.courses.api.request;

import static com.courses.api.validations.BeanValidation.REQUIRED_DESCRIPTION;
import static com.courses.api.validations.BeanValidation.REQUIRED_DURATION;
import static com.courses.api.validations.BeanValidation.REQUIRED_NAME;

import java.util.List;

import static com.courses.api.validations.BeanValidation.REQUIRED_CATEGORY;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CourseRequest {

	@NotBlank(message = REQUIRED_NAME)
	private String name;

	@NotBlank(message = REQUIRED_DESCRIPTION)
	private String description;

	@NotNull(message = REQUIRED_DURATION)
	private Integer duration;
	
	@NotEmpty(message = REQUIRED_CATEGORY)
	private List<Long> categoriesId;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDuration(){
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public List<Long> getCategoriesId() {
		return categoriesId;
	}
	
	public void setCategoriesId(List<Long> categoriesId) {
		this.categoriesId = categoriesId;
	}
}
