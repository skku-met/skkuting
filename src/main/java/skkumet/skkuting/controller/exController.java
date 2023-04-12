package skkumet.skkuting.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class exController {

    @GetMapping("/ex")
    public String getException() {
        log.info("hello ex controller");
        return "Hello this";
    }

    @GetMapping("/ex2")
    public String getEx2() {
        log.info("hello ex controller");
        return "Hello ex2";
    }
}
