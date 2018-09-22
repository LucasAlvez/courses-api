package com.courses.api.response;

import java.util.List;

public class CourseResponse {

	private Long id;

	private String name;

	private String description;
	
	private String createDate;
	
	private String updateDate;
	
	private List<String> catgories;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public String getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	public List<String> getCatgories() {
		return catgories;
	}
	
	public void setCatgories(List<String> catgories) {
		this.catgories = catgories;
	}
}
