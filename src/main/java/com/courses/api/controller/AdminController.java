package com.courses.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.courses.api.enums.CourseSort;
import com.courses.api.enums.SortOrder;
import com.courses.api.request.CategoryRequest;
import com.courses.api.response.CategoryResponse;
import com.courses.api.response.CourseResponse;
import com.courses.api.service.CategoryService;
import com.courses.api.service.CourseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "admin", description = "Administrativo")
@RequestMapping(value = "/v1/admin")
public class AdminController extends Controller {

	@Autowired
	CategoryService categoryService;

	@Autowired
	CourseService courseService;
	
	@ApiOperation(value = "Cria uma categoria")
	@RequestMapping(value = "categories", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public CategoryResponse create(@RequestBody @Valid CategoryRequest request, BindingResult result)
			throws ResourceNotFoundException, Exception {
		verifyInvalidParam(result);
		return categoryService.create(request);
	}
	
	@ApiOperation(value = "Atuazaliza uma categoria")
	@RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public CategoryResponse update(@RequestBody @Valid CategoryRequest request,
			@PathVariable("categoryId") @Valid Long categoryId, BindingResult result) throws Exception {
		verifyInvalidParam(result);
		return categoryService.update(request, categoryId);
	}
	
	@ApiOperation(value = "Lista todos os cursos")
	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public Page<CourseResponse> listAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "sortBy", defaultValue = "id") CourseSort sortBy,
			@RequestParam(value = "sortOrder", defaultValue = "DESC") SortOrder sortOrder) throws Exception {
		return courseService.listAll(page, size, sortBy, sortOrder);
	}
}
