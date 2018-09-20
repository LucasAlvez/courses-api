package com.courses.api.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.courses.api.entity.CourseEntity;
import com.courses.api.request.CourseRequest;
import com.courses.api.response.CourseResponse;

public class CourseBuilder {
	
	public static CourseEntity buildRequest(CourseRequest request)
			throws Exception {
		CourseEntity entity = new CourseEntity();
		entity.setName(request.getName());
		entity.setDescription(request.getDescription());
		entity.setCreatedAt(LocalDateTime.now());
		return entity;
	}

	public static CourseEntity buildUpdateRequest(CourseRequest request, CourseEntity entity) {
		entity.setName(request.getName());
		entity.setDescription(request.getDescription());
		return entity;
	}
	
	public static CourseResponse buildResponse(CourseEntity entity) {
		CourseResponse response = new CourseResponse();
		response.setId(entity.getId());
		response.setName(entity.getName());
		response.setDescription(entity.getDescription());
		response.setCreatedAt(entity.getCreatedAt().toString());		
		return response;
	}

	public static List<CourseResponse> to(List<CourseEntity> course) {
		List<CourseResponse> list = new ArrayList<>();
		if (course.isEmpty() || course == null)
			return list;

		course.forEach(e -> {
			list.add(buildResponse(e));
		});
		return list;
	}
}
