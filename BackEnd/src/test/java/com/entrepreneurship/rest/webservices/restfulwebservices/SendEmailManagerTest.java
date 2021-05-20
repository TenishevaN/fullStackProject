package com.entrepreneurship.rest.webservices.restfulwebservices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.entrepreneurship.rest.webservices.restfulwebservices.emailing.MailSenderService;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.UserDao;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SendEmailManagerTest {

	@Autowired
	private MailSenderService senderService;

	@Autowired
	private UserDao userDao;

	private static final Logger loggerInfo = LoggerFactory.getLogger("logger.info");

	@Test
	public void sendEmail() {

		try {
			senderService.sendSimpleMail(userDao.findByUsername("admin").getEmail(), "Test", "Test");
		} catch (Exception e) {
			loggerInfo.info("Check email service security. Maybe you should set less security app on.");
		}
	}

}
