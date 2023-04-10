package skkumet.skkuting.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import skkumet.skkuting.util.ErrorCode;
import skkumet.skkuting.util.exception.User.UserSignupException;

@Slf4j
@RestController
public class exController {

    @GetMapping("/ex")
    public String getException(){
        log.info("hello ex controller");
        return "Hello this";
    }

    @GetMapping("/ex2")
    public String getEx2(){
        log.info("hello ex controller");
        return "Hello ex2";
    }
}
