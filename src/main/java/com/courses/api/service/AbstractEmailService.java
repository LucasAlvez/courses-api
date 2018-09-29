package com.courses.api.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.courses.api.entity.StudentEntity;

public abstract class AbstractEmailService implements EmailService {
		
	@Value("${default.sender}")
	private String sender;

	@Override
	public void confirmationOfRegistration (StudentEntity student) {
		SimpleMailMessage sm = prepareSimpleMailMessage(student);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessage(StudentEntity student) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo("luucasalves.p@gmail.com");
		sm.setFrom(sender);
		sm.setSubject("Confirmação de cadastro");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Olá, " + student.getEmail() + "\n" +
					"sua conta com o e-mail " + student.getEmail() + " foi criada com sucesso!");
		return sm;
	}
}
