package com.courses.api.builder;

import java.util.ArrayList;
import java.util.List;

import com.courses.api.entity.AccountEntity;
import com.courses.api.entity.StudentEntity;
import com.courses.api.response.StudentResponse;

public class StudentBuilder {

	public static StudentEntity buildUpdateRequest(AccountEntity account) {
		StudentEntity entity = new StudentEntity();
		entity.setAccount(account);
		return entity;
	}

	public static StudentResponse buildResponse(StudentEntity entity) {
		StudentResponse response = new StudentResponse();
		response.setId(entity.getId());
		response.setAccount(AccountBuilder.buildResponse(entity.getAccount()));
		return response;
	}

	public static List<StudentResponse> to(List<StudentEntity> entity) {
		List<StudentResponse> list = new ArrayList<>();
		if (entity.isEmpty() || entity == null)
			return list;

		entity.forEach(account -> {
			list.add(buildResponse(account));
		});
		return list;
	}

}
