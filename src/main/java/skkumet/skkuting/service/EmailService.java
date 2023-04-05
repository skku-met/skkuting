package skkumet.skkuting.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import skkumet.skkuting.util.RedisUtil;

import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final RedisUtil redisUtil;
//    private static final String ADMIN_ADDRESS = "skkuting@naver.com";

    public void sendMail(String email) throws MessagingException {
        if (redisUtil.existData(email)) {
            redisUtil.deleteData(email);
        }
        MimeMessage message = createEmailForm(email);
        mailSender.send(message);
    }

    private MimeMessage createEmailForm(String email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setFrom("linuxs79267926@gmail.com");
        message.setSubject("[skkuting 이메일 인증 요청]");
        String authCode = createCode();
        redisUtil.setDataWithExpire(email,authCode,60*30L);
        message.setText(authCode);
        return message;
    }

    public boolean validateEmailCode(String email, String code) {
        return Optional.ofNullable(redisUtil.getData(email))
                .map(s -> s.equals(code))
                .orElse(false);
    }

    private static String createCode() {
        StringBuilder text = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int v = random.nextInt(10) + 1;
            text.append(v);
        }
        return text.toString();
    }

}