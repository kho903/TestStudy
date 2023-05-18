package com.jikim.cafekiosk.spring.api.service.mail;

import org.springframework.stereotype.Service;

import com.jikim.cafekiosk.spring.client.mail.MailSendClient;
import com.jikim.cafekiosk.spring.domain.history.mail.MailSendHistory;
import com.jikim.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {

	private final MailSendClient mailSendClient;
	private final MailSendHistoryRepository mailSendHistoryRepository;

	public boolean sendMail(String fromEmail, String toEmail, String subject, String content) {

		boolean result = mailSendClient.sendEmail(fromEmail, toEmail, subject, content);
		if (result) {
			mailSendHistoryRepository.save(MailSendHistory.builder()
				.fromEmail(fromEmail)
				.toEmail(toEmail)
				.subject(subject)
				.content(content)
				.build()
			);
			return true;
		}
		return false;
	}
}
