package com.courses.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.courses.api.enums.CategorySort;
import com.courses.api.enums.SortOrder;
import com.courses.api.request.CategoryRequest;
import com.courses.api.response.CategoryResponse;
import com.courses.api.security.Permissions;
import com.courses.api.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "categories", description = "Categorias")
@RequestMapping(value = "/v1/categories")
public class CategoryController extends Controller {

	@Autowired
	CategoryService categoryService;

	@ApiOperation(value = "Cria uma categoria")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	//@PreAuthorize(Permissions.ADMIN)
	public CategoryResponse create(@RequestBody @Valid CategoryRequest request, BindingResult result)
			throws ResourceNotFoundException, Exception {
		verifyInvalidParam(result);
		return categoryService.create(request);
	}

	@ApiOperation(value = "Busca categoria pelo seu id")
	@RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	//@PreAuthorize(Permissions.DEFAULT)
	public CategoryResponse findById(@PathVariable("categoryId") @Valid Long categoryId) throws Exception {
		return categoryService.findById(categoryId);
	}

	@ApiOperation(value = "Atuazaliza uma categoria")
	@RequestMapping(value = "/{categoryId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	//@PreAuthorize(Permissions.ADMIN)
	public CategoryResponse update(@RequestBody @Valid CategoryRequest request,
			@PathVariable("categoryId") @Valid Long categoryId, BindingResult result) throws Exception {
		verifyInvalidParam(result);
		return categoryService.update(request, categoryId);
	}

	@ApiOperation(value = "Lista todos as categorias da plataforma")
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	//@PreAuthorize(Permissions.DEFAULT)
	public Page<CategoryResponse> listAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "sortBy", defaultValue = "id") CategorySort sortBy,
			@RequestParam(value = "sortOrder", defaultValue = "DESC") SortOrder sortOrder) throws Exception {
		return categoryService.listAll(page, size, sortBy, sortOrder);
	}

	@ApiOperation(value = "Deleta uma categoria")
	@RequestMapping(value = "/{categoryId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	//@PreAuthorize(Permissions.ADMIN)
	public void delete(@PathVariable("categoryId") @Valid Long categoryId) throws Exception {
		categoryService.delete(categoryId);
	}
}
