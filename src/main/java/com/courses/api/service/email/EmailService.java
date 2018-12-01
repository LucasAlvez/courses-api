package com.courses.api.service.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.courses.api.entity.UserEntity;

@Service
public interface EmailService {
	
	public void confirmationOfRegistration (UserEntity account);
	
	public void sendEmail(SimpleMailMessage msg);
}
