package skkumet.skkuting.service;

import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import skkumet.skkuting.util.RedisUtil;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@DisplayName("이메일 인증 테스트")
@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @InjectMocks EmailService sut;
    @Mock RedisUtil redisUtil;
    @Mock private JavaMailSender mailSender;

    @Test
    public void testCreateEmailFormWithValidEmail() throws MessagingException, IOException {
        String email = "user@example.com";
        MimeMessage mockMessage = new MimeMessage(Session.getDefaultInstance(new Properties()));
        when(mailSender.createMimeMessage()).thenReturn(mockMessage);
//
        MimeMessage message = sut.createEmailForm(email);

        assertNotNull(message);
        assertEquals(new InternetAddress(email), message.getRecipients(MimeMessage.RecipientType.TO)[0]);
        assertEquals(new InternetAddress("linuxs79267926@gmail.com"), message.getFrom()[0]);
        assertEquals("[skkuting 이메일 인증 요청]", message.getSubject());
    }

}