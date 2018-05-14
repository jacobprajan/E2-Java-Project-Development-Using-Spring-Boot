package com.dev.flightreservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Value("${com.dev.flightreservation.itinerary.email.subject}")
	private String EMAIL_SUBJECT = "Itinerary for your Flight";

	@Value("${com.dev.flightreservation.itinerary.email.body}")
	private String EMAIL_BODY = "Please find you Itinerary attached";

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

	@Autowired
	private JavaMailSender sender;

	public void sendItinerary(String toAddress, String filePath) {

		LOGGER.info("Inside sendItinerary()");
		MimeMessage message = sender.createMimeMessage();

		try {
			// For sending attachments, second param needs to set as true in
			// MimeMessageHelper. It is false by default.
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setTo(toAddress);
			messageHelper.setSubject(EMAIL_SUBJECT);
			messageHelper.setText(EMAIL_BODY);
			messageHelper.addAttachment("Itinerary", new File(filePath));

			sender.send(message);

		} catch (MessagingException e) {
			LOGGER.error("Exception inside sendItinerary " + e);
		}

	}

}