package com.courses.api.service;

import org.springframework.mail.SimpleMailMessage;

import com.courses.api.entity.UserEntity;

public interface EmailService {
	
	public void confirmationOfRegistration (UserEntity user);
	
	public void sendEmail(SimpleMailMessage msg);
}
