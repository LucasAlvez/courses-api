package com.courses.api.service.email;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.courses.api.entity.UserEntity;

public abstract class AbstractEmailService implements EmailService {
		
	@Value("${default.sender}")
	private String sender;

	@Override
	public void confirmationOfRegistration (UserEntity user) {
		SimpleMailMessage sm = prepareSimpleMailMessage(user);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessage(UserEntity user) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(user.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Confirmação de cadastro");
//		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Olá, " + user.getName() + "\n" +
					"sua conta com o e-mail " + user.getEmail() + " foi criada com sucesso!");
		return sm;
	}
}
