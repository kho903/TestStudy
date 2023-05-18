package com.jikim.cafekiosk.spring.api.service.mail;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jikim.cafekiosk.spring.client.mail.MailSendClient;
import com.jikim.cafekiosk.spring.domain.history.mail.MailSendHistory;
import com.jikim.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {


	// @Mock vs @Spy
	// @Mock의 경우는 MailSendClient 내의 a, b, c 동작 X. - Stubbing이 되지 않음.
	// @Spy의 경우는 MailSendClient 내의 a, b, c 의 경우는 실제 객체 동작.
	// @Mock
	@Spy
	private MailSendClient mailSendClient;

	@Mock
	private MailSendHistoryRepository mailSendHistoryRepository;

	@InjectMocks
	private MailService mailService;

	@DisplayName("메일 전송 테스트")
	@Test
	void sendMail() throws Exception {
	    // given
		// 위의 @Mock으로 대체 가능
		// MailSendClient mailSendClient = mock(MailSendClient.class);
		// MailSendHistoryRepository mailSendHistoryRepository = mock(MailSendHistoryRepository.class);

		// 위의 @InjectMocks로 대체 가능
		// MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

		// when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
		// 	.thenReturn(true);

		// @Spy 를 사용할 경우 when 절 X
		doReturn(true)
			.when(mailSendClient)
			.sendEmail(anyString(), anyString(), anyString(), anyString());

		// when
		boolean result = mailService.sendMail("", "", "", "");

		// then
		assertThat(result).isTrue();
		verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
	}
}