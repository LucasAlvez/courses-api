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

import com.courses.api.builder.CourseBuilder;
import com.courses.api.entity.CategoryEntity;
import com.courses.api.entity.CourseEntity;
import com.courses.api.enums.CourseSort;
import com.courses.api.enums.SortOrder;
import com.courses.api.repository.CategoryRepository;
import com.courses.api.repository.CourseRepository;
import com.courses.api.request.CourseRequest;
import com.courses.api.response.CourseResponse;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	CategoryRepository categoryRepository;

	public CourseResponse create(CourseRequest request) throws ResourceNotFoundException, Exception {

		List<CategoryEntity> categories = categoryRepository.findAllById(request.getCategories());
		CourseEntity course = CourseBuilder.buildRequest(request, categories);
		CourseEntity newCourse = courseRepository.save(course);

		return CourseBuilder.buildResponse(newCourse);
	}

	public CourseResponse update(CourseRequest request, Long courseId) throws Exception, ResourceNotFoundException {

		CourseEntity courseById = getCourseById(courseId);
		List<CategoryEntity> categories = categoryRepository.findAllById(request.getCategories());

		CourseEntity course = CourseBuilder.buildUpdateRequest(request, courseById, categories);
		CourseEntity newCourse = courseRepository.save(course);
		return CourseBuilder.buildResponse(newCourse);
	}

	public CourseResponse findById(Long courseId) {
		CourseEntity course = getCourseById(courseId);
		return CourseBuilder.buildResponse(course);
	}

	public Page<CourseResponse> listAll(Integer page, Integer size, CourseSort sortBy, SortOrder sortOrder) {
		PageRequest pageRequest = null;

		if (SortOrder.ASC.equals(sortOrder)) {
			pageRequest = PageRequest.of(page, size, Direction.ASC, sortBy.toString());
		}

		if (SortOrder.DESC.equals(sortOrder)) {
			pageRequest = PageRequest.of(page, size, Direction.DESC, sortBy.toString());
		}

		Page<CourseEntity> courses = courseRepository.findAll(pageRequest);

		List<CourseResponse> coursesResponse = new ArrayList<>();
		for (CourseEntity c : courses) {
			coursesResponse.add(CourseBuilder.buildResponse(c));
		}

		return new PageImpl<>(coursesResponse, pageRequest, courseRepository.findAll(pageRequest).getSize());
	}

	public void delete(Long courseId) throws ResourceNotFoundException {
		CourseEntity course = getCourseById(courseId);
		courseRepository.delete(course);
	}

	private CourseEntity getCourseById(Long courseId) throws ResourceNotFoundException {
		Optional<CourseEntity> course = courseRepository.findById(courseId);
		course.orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));
		return course.get();
	}
}
