package com.courses.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.courses.api.builder.CategoryBuilder;
import com.courses.api.entity.AccountEntity;
import com.courses.api.entity.CategoryEntity;
import com.courses.api.entity.UserEntity;
import com.courses.api.enums.CategorySort;
import com.courses.api.enums.SortOrder;
import com.courses.api.repository.CategoryRepository;
import com.courses.api.request.CategoryRequest;
import com.courses.api.response.CategoryResponse;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public CategoryResponse create(CategoryRequest request) throws Exception {
		Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
		userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));

		AccountEntity account = userLogged.get().getAccount();

		CategoryEntity category = CategoryBuilder.buildRequest(request, account);
		CategoryEntity newCategory = categoryRepository.save(category);
		return CategoryBuilder.buildResponse(newCategory);
	}

	public CategoryResponse update(CategoryRequest request, Long categoryId)
			throws Exception, ResourceNotFoundException {
		Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
		userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));

		AccountEntity account = userLogged.get().getAccount();

		CategoryEntity categoryById = getCategoryByid(categoryId);

		validateCategory(categoryById, account);

		CategoryEntity category = CategoryBuilder.buildUpdateRequest(request, categoryById);
		CategoryEntity newCategory = categoryRepository.save(category);
		return CategoryBuilder.buildResponse(newCategory);
	}

	public CategoryResponse findById(Long categoryId) throws Exception {
		Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
		userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));

		AccountEntity account = userLogged.get().getAccount();

		CategoryEntity category = getCategoryByid(categoryId);
		validateCategory(category, account);

		return CategoryBuilder.buildResponse(category);
	}


	public Page<CategoryResponse> listAll(Integer page, Integer size, CategorySort sortBy, SortOrder sortOrder) {
		PageRequest pageRequest = null;

		if (SortOrder.ASC.equals(sortOrder)) {
			pageRequest = PageRequest.of(page, size, Direction.ASC, sortBy.toString());
		}

		if (SortOrder.DESC.equals(sortOrder)) {
			pageRequest = PageRequest.of(page, size, Direction.DESC, sortBy.toString());
		}

		Page<CategoryEntity> categories = categoryRepository.findAll(pageRequest);

		List<CategoryResponse> categoryResponse = new ArrayList<>();
		for (CategoryEntity c : categories) {
			categoryResponse.add(CategoryBuilder.buildResponse(c));
		}

		return new PageImpl<>(categoryResponse, pageRequest, categoryResponse.size());
	}

	public void delete(Long categoryId) throws Exception {
		Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
		userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));

		AccountEntity account = userLogged.get().getAccount();

		CategoryEntity category = getCategoryByid(categoryId);
		validateCategory(category, account);

		categoryRepository.delete(category);
	}

	private void validateCategory(CategoryEntity category, AccountEntity account) throws Exception {
		if (!category.getAccount().getId().equals(account.getId())) {
			throw new Exception("Categoria não pertence ao usuário");
		}
	}

	private CategoryEntity getCategoryByid(Long categoryId) throws ResourceNotFoundException {
		Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
		category.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrado"));
		return category.get();
	}

}
