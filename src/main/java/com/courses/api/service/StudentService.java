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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.courses.api.builder.StudentBuilder;
import com.courses.api.entity.StudentEntity;
import com.courses.api.enums.SortOrder;
import com.courses.api.enums.StudentSort;
import com.courses.api.repository.StudentRepository;
import com.courses.api.request.StudentRequest;
import com.courses.api.request.StudentUpdateRequest;
import com.courses.api.response.StudentResponse;
import com.courses.api.service.email.EmailService;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	EmailService emailService;

	@Autowired
	BCryptPasswordEncoder criptPass;

	public StudentResponse create(StudentRequest request) {

		StudentEntity student = StudentBuilder.buildRequest(request);
		StudentEntity newStudent = studentRepository.save(student);

		return StudentBuilder.buildResponse(newStudent);
	}
	
	public StudentResponse update(StudentUpdateRequest request, Long studentId) throws Exception, ResourceNotFoundException {

		StudentEntity studentById = getStudentById(studentId);

		StudentEntity student = StudentBuilder.buildUpdateRequest(request, studentById);
		StudentEntity newStudent = studentRepository.save(student);
		return StudentBuilder.buildResponse(newStudent);
	}

	public StudentResponse findById(Long studentId) {
		StudentEntity student = getStudentById(studentId);
		return StudentBuilder.buildResponse(student);
	}

	public Page<StudentResponse> listAll(Integer page, Integer size, StudentSort sortBy, SortOrder sortOrder) {
		PageRequest pageRequest = null;

		if (SortOrder.ASC.equals(sortOrder)) {
			pageRequest = PageRequest.of(page, size, Direction.ASC, sortBy.toString());
		}

		if (SortOrder.DESC.equals(sortOrder)) {
			pageRequest = PageRequest.of(page, size, Direction.DESC, sortBy.toString());
		}

		Page<StudentEntity> students = studentRepository.findAll(pageRequest);

		List<StudentResponse> studentResponse = new ArrayList<>();
		for (StudentEntity c : students) {
			studentResponse.add(StudentBuilder.buildResponse(c));
		}

		return new PageImpl<>(studentResponse, pageRequest, studentRepository.findAll(pageRequest).getSize());
	}

	public void delete(Long studentId) {
		StudentEntity student = getStudentById(studentId);
		studentRepository.delete(student);
	}

	private StudentEntity getStudentById(Long studentId) throws ResourceNotFoundException {
		Optional<StudentEntity> student = studentRepository.findById(studentId);
		student.orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
		return student.get();
	}
}
