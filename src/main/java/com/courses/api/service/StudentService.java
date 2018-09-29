package com.courses.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.courses.api.builder.StudentBuilder;
import com.courses.api.entity.StudentEntity;
import com.courses.api.repository.StudentRepository;
import com.courses.api.request.StudentRequest;
import com.courses.api.response.StudentResponse;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	BCryptPasswordEncoder criptPass;
	
	public StudentResponse create (StudentRequest request) {
		
		request.setPass(criptPass.encode(request.getPass()));
		
		
		StudentEntity student = StudentBuilder.buildRequest(request);
		StudentEntity newStudent = studentRepository.save(student);
		
		emailService.confirmationOfRegistration(newStudent);
		
		return StudentBuilder.buildResponse(newStudent);
	}
}
