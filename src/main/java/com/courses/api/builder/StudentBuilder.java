package com.courses.api.builder;

import java.util.ArrayList;
import java.util.List;

import com.courses.api.entity.StudentEntity;
import com.courses.api.enums.Roles;
import com.courses.api.request.StudentRequest;
import com.courses.api.request.StudentUpdateRequest;
import com.courses.api.response.StudentResponse;

public class StudentBuilder {

	public static StudentEntity buildRequest(StudentRequest request) {
		StudentEntity entity = new StudentEntity();
		entity.setEmail(request.getEmail());
		entity.setPass(request.getPass());
		entity.addRoles(Roles.ADMIN);
		return entity;
	}

	public static StudentEntity buildUpdateRequest(StudentUpdateRequest request, StudentEntity entity) {
		entity.setEmail(request.getEmail());
		entity.setPass(request.getPass());
		return entity;
	}

	public static StudentResponse buildResponse(StudentEntity entity) {
		StudentResponse response = new StudentResponse();
		response.setId(entity.getId());
		response.setEmail(entity.getEmail());
		response.setRoles(entity.getRoles().toString());
		return response;
	}

	public static List<StudentResponse> to(List<StudentEntity> entity) {
		List<StudentResponse> list = new ArrayList<>();
		if (entity.isEmpty() || entity == null)
			return list;

		entity.forEach(student -> {
			list.add(buildResponse(student));
		});
		return list;
	}
}
