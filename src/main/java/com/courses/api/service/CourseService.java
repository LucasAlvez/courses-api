package com.courses.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.courses.api.builder.CourseBuilder;
import com.courses.api.entity.CategoryEntity;
import com.courses.api.entity.CourseEntity;
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

	public List<CourseResponse> listAll() {
		List<CourseEntity> course = courseRepository.findAll();
		return CourseBuilder.to(course);
	}

	public void delete(Long courseId) throws ResourceNotFoundException {
		CourseEntity course = getCourseById(courseId);
		courseRepository.delete(course);
	}
	
	public Page<CourseEntity> listAll (Integer page, Integer size, String sortBy, String sortOrder) {
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(sortOrder), sortBy);
		return courseRepository.findAll(pageRequest);
	}

	private CourseEntity getCourseById(Long courseId) throws ResourceNotFoundException {
		Optional<CourseEntity> course = courseRepository.findById(courseId);
		course.orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));
		return course.get();
	}
}
