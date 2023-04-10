package skkumet.skkuting.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skkumet.skkuting.service.EmailService;

@RequiredArgsConstructor
@RequestMapping("/email")
@RestController
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/authcode")
    public ResponseEntity<String> sendEmail(@RequestParam String email) throws MessagingException {
        emailService.sendMail(email);
        return ResponseEntity.ok().body("ok");
    }


}
