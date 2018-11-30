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
import com.courses.api.builder.StudentBuilder;
import com.courses.api.entity.AccountEntity;
import com.courses.api.entity.CategoryEntity;
import com.courses.api.entity.CourseEntity;
import com.courses.api.entity.StudentEntity;
import com.courses.api.entity.UserEntity;
import com.courses.api.enums.CourseSort;
import com.courses.api.enums.SortOrder;
import com.courses.api.enums.StudentSort;
import com.courses.api.repository.AccountRepository;
import com.courses.api.repository.CategoryRepository;
import com.courses.api.repository.CourseRepository;
import com.courses.api.repository.StudentRepository;
import com.courses.api.request.CourseRequest;
import com.courses.api.response.CourseResponse;
import com.courses.api.response.StudentResponse;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	StudentRepository studentRepository;

	public CourseResponse create(CourseRequest request) throws ResourceNotFoundException, Exception {
		Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
		userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));

		AccountEntity account = userLogged.get().getAccount();

		List<CategoryEntity> categories = categoryRepository.findAllById(request.getCategories());

		CourseEntity course = CourseBuilder.buildRequest(request, categories, account);
		CourseEntity newCourse = courseRepository.save(course);
		
		account.setIsProducer(true);
		accountRepository.save(account);

		return CourseBuilder.buildResponse(newCourse);
	}

	public CourseResponse update(CourseRequest request, Long courseId) throws Exception, ResourceNotFoundException {
		Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
		userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));

		AccountEntity account = userLogged.get().getAccount();

		CourseEntity courseById = getCourseById(courseId);

		validateCourse(courseById, account);

		List<CategoryEntity> categories = categoryRepository.findAllById(request.getCategories());

		CourseEntity course = CourseBuilder.buildUpdateRequest(request, courseById, categories);
		CourseEntity newCourse = courseRepository.save(course);
		return CourseBuilder.buildResponse(newCourse);
	}

	public CourseResponse addStudent(Long courseId) throws Exception {
		Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
		userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));

		AccountEntity account = userLogged.get().getAccount();

		CourseEntity courseById = getCourseById(courseId);
		
		validateStudent(courseById, account);
		
		StudentEntity student = StudentBuilder.buildUpdateRequest(account);
		studentRepository.save(student);
		
		account.setIsStudent(true);
		accountRepository.save(account);

		courseById.getStudents().add(student);
		
		CourseEntity newCourse = courseRepository.save(courseById);

		return CourseBuilder.buildResponse(newCourse);
	} 

	public CourseResponse findById(Long courseId) throws Exception {
		Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
		userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));

		AccountEntity account = userLogged.get().getAccount();

		CourseEntity course = getCourseById(courseId);

		validateCourse(course, account);

		return CourseBuilder.buildResponse(course);
	}
	
	public Page<StudentResponse> listStudentsByCourse(Long courseId, Integer page, Integer size, StudentSort sortBy, SortOrder sortOrder) throws Exception {
		Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
		userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));

		AccountEntity account = userLogged.get().getAccount();

		CourseEntity course = getCourseById(courseId);

		validateCourse(course, account);
		
		PageRequest pageRequest = null;

		if (SortOrder.ASC.equals(sortOrder)) {
			pageRequest = PageRequest.of(page, size, Direction.ASC, sortBy.toString());
		}

		if (SortOrder.DESC.equals(sortOrder)) {
			pageRequest = PageRequest.of(page, size, Direction.DESC, sortBy.toString());
		}
		
		List<StudentResponse> response = new ArrayList<>();
		
		for(StudentEntity student : course.getStudents()) {
			response.add(StudentBuilder.buildResponse(student));
		}

		return new PageImpl<>(response, pageRequest, response.size());
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

		return new PageImpl<>(coursesResponse, pageRequest, coursesResponse.size());
	}

	public void delete(Long courseId) throws Exception {
		Optional<UserEntity> userLogged = Optional.ofNullable(UserService.getUserLogged());
		userLogged.orElseThrow(() -> new Exception("Usuário não encontrado"));

		AccountEntity account = userLogged.get().getAccount();

		CourseEntity course = getCourseById(courseId);

		validateCourse(course, account);

		courseRepository.delete(course);
	}

	private CourseEntity getCourseById(Long courseId) throws ResourceNotFoundException {
		Optional<CourseEntity> course = courseRepository.findById(courseId);
		course.orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));
		return course.get();
	}

	private void validateCourse(CourseEntity course, AccountEntity account) throws Exception {
		if (!course.getAccount().getId().equals(account.getId())) {
			throw new Exception("Curso não pertence ao usuário");
		}
	}
	
	private void validateStudent(CourseEntity course, AccountEntity account) throws Exception {		
		if (course.getStudents().contains(account.getStudent())) {
			throw new Exception("Aluno já está neste curso");
		}
	}
}
