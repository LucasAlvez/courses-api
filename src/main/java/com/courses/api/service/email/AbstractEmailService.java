package com.courses.api.service.email;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.courses.api.entity.UserEntity;

@Service
public abstract class AbstractEmailService implements EmailService {
		
	@Value("${default.sender}")
	private String sender;

	@Override
	public void confirmationOfRegistration (UserEntity user) {
		SimpleMailMessage sm = confirmMailMessage(user);
		sendEmail(sm);
	}
	
	@Override
	public void sendNewPassword (UserEntity user, String newPass) {
		SimpleMailMessage sm = senPasswordMailMessage(user, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage confirmMailMessage(UserEntity user) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(user.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Confirmação de cadastro");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Olá, " + user.getName() + "\n" +
					"sua conta com o e-mail " + user.getEmail() + " foi criada com sucesso!");
		return sm;
	}
	
	protected SimpleMailMessage senPasswordMailMessage(UserEntity user, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(user.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Sua nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Olá, " + user.getName() + "\n" +
					"como solicitado, segue abaixo sua nova senha de acesso:\n" + "Nova senha de acesso: " + newPass);
		return sm;
	}
}
