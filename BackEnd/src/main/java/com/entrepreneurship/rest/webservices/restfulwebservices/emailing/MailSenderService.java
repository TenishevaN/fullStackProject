package com.entrepreneurship.rest.webservices.restfulwebservices.emailing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/*
 * MailSenderService is a service class which helps to send email.
@version 1.0
@author from tutorial
 */

@Service
public class MailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendSimpleMail(String reciever, String letter_subject, String letter_content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(reciever);
		message.setSubject(letter_subject);
		message.setText(letter_content);
		try {
			mailSender.send(message);
		} catch (Exception e) {

		}
	}

}
