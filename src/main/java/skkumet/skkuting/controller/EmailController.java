package skkumet.skkuting.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skkumet.skkuting.service.EmailService;

@RequiredArgsConstructor
@RestController
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/authcode")
    public void sendEmail(@RequestParam String email) throws MessagingException {
        emailService.sendMail(email);
    }

}
