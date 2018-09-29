package com.courses.api.service;

import org.springframework.mail.SimpleMailMessage;

import com.courses.api.entity.StudentEntity;

public interface EmailService {
	
	public void confirmationOfRegistration (StudentEntity student);
	
	public void sendEmail(SimpleMailMessage msg);
}
