package com.courses.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.courses.api.enums.CourseSort;
import com.courses.api.enums.SortOrder;
import com.courses.api.response.CourseResponse;
import com.courses.api.service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "students", description = "Alunos")
@RequestMapping(value = "/v1/students")
public class StudentController extends Controller {
	
	@Autowired
	StudentService studentService;

	@ApiOperation(value = "Busca todos os cursos do aluno logado")
	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	// @PreAuthorize(Permissions.DEFAULT)
	public Page<CourseResponse> listCoursesByStudent(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "sortBy", defaultValue = "id") CourseSort sortBy,
			@RequestParam(value = "sortOrder", defaultValue = "DESC") SortOrder sortOrder) throws Exception {
		return studentService.listCoursesByStudent(page, size, sortBy, sortOrder);
	}
}
