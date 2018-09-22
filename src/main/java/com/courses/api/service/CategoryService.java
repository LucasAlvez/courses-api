package com.courses.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.courses.api.builder.CategoryBuilder;
import com.courses.api.entity.CategoryEntity;
import com.courses.api.repository.CategoryRepository;
import com.courses.api.request.CategoryRequest;
import com.courses.api.response.CategoryResponse;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public CategoryResponse create (CategoryRequest request) {
		
		CategoryEntity category = CategoryBuilder.buildRequest(request);
		CategoryEntity newCategory = categoryRepository.save(category);
		
		return CategoryBuilder.buildResponse(newCategory);
	}
	
	public CategoryResponse update(CategoryRequest request, Long categoryId)
			throws Exception, ResourceNotFoundException {

		CategoryEntity categoryById = getCategoryByid(categoryId);

		CategoryEntity category = CategoryBuilder.buildUpdateRequest(request, categoryById);
		CategoryEntity newCategory = categoryRepository.save(category);
		return CategoryBuilder.buildResponse(newCategory);
	}
	
	public List<CategoryResponse> listAll() {
		List<CategoryEntity> categories = categoryRepository.findAll();
		return CategoryBuilder.to(categories);
	}
	
	public void delete (Long categoryId) {
		CategoryEntity category = getCategoryByid(categoryId);
		categoryRepository.delete(category);
	}
	
	private CategoryEntity getCategoryByid(Long categoryId) throws ResourceNotFoundException {
		Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
		category.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrado"));
		return category.get();
	}

}
