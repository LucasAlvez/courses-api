package com.courses.api.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.courses.api.entity.AccountEntity;
import com.courses.api.entity.CategoryEntity;
import com.courses.api.entity.CourseEntity;
import com.courses.api.request.CourseRequest;
import com.courses.api.response.CourseResponse;

public class CourseBuilder {

	public static CourseEntity buildRequest(CourseRequest request, List<CategoryEntity> categories,
			AccountEntity account) throws Exception {
		CourseEntity entity = new CourseEntity();
		entity.setName(request.getName());
		entity.setDescription(request.getDescription());
		entity.setDuration(request.getDuration());
		entity.setCreateDate(LocalDateTime.now());
		entity.setUpdateDate(LocalDateTime.now());
		entity.setCategories(categories);
		entity.setAccount(account);
		return entity;
	}

	public static CourseEntity buildUpdateRequest(CourseRequest request, CourseEntity entity,
			List<CategoryEntity> categories) {
		entity.setName(request.getName());
		entity.setDescription(request.getDescription());
		entity.setCategories(categories);
		entity.setUpdateDate(LocalDateTime.now());
		return entity;
	}

	public static CourseResponse buildResponse(CourseEntity entity) {
		CourseResponse response = new CourseResponse();
		response.setId(entity.getId());
		response.setName(entity.getName());
		response.setDescription(entity.getDescription());
		response.setCreateDate(entity.getCreateDate().toString());
		response.setUpdateDate(entity.getUpdateDate().toString());
		response.setCategories(CategoryBuilder.to(entity.getCategories()));
		response.setProducer(AccountBuilder.buildResponse(entity.getAccount()));
		if (entity.getStudents() != null) { response.setStudents(StudentBuilder.to(entity.getStudents())); }
		return response;
	}

	public static List<CourseResponse> to(List<CourseEntity> entity) {
		List<CourseResponse> list = new ArrayList<>();
		if (entity.isEmpty() || entity == null)
			return list;

		entity.forEach(course -> {
			list.add(buildResponse(course));
		});
		return list;
	}
}
