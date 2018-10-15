package com.courses.api.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.courses.api.entity.CategoryEntity;
import com.courses.api.request.CategoryRequest;
import com.courses.api.response.CategoryResponse;

public class CategoryBuilder {

	public static CategoryEntity buildRequest(CategoryRequest request) {
		CategoryEntity entity = new CategoryEntity();
		entity.setName(request.getName());
		entity.setCreateDate(LocalDateTime.now());
		entity.setUpdateDate(LocalDateTime.now());
		return entity;
	}

	public static CategoryEntity buildUpdateRequest(CategoryRequest request, CategoryEntity entity) {
		entity.setName(request.getName());
		entity.setUpdateDate(LocalDateTime.now());
		return entity;
	}

	public static CategoryResponse buildResponse(CategoryEntity entity) {
		CategoryResponse response = new CategoryResponse();
		response.setId(entity.getId());
		response.setName(entity.getName());
		response.setCreateDate(entity.getCreateDate().toString());
		response.setUpdateDate(entity.getUpdateDate().toString());
		return response;
	}

	public static List<CategoryResponse> to(List<CategoryEntity> categories) {
		List<CategoryResponse> list = new ArrayList<>();
		if (categories.isEmpty() || categories == null)
			return list;

		categories.forEach(category -> {
			list.add(buildResponse(category));
		});
		return list;
	}
}
