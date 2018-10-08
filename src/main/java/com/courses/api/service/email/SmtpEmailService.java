package com.courses.api.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService {
	
	@Autowired
	private MailSender mailSender;

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		mailSender.send(msg);
	}

}
