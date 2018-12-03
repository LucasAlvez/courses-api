package com.courses.api.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SmtpEmailService extends AbstractEmailService {
	
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		mailSender.send(msg);
	}
}
