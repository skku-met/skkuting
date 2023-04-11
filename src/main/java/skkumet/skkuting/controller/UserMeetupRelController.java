package skkumet.skkuting.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import skkumet.skkuting.dto.UserAccountDto;
import skkumet.skkuting.dto.UserAccountPrincipal;

@Slf4j
@RestController
public class UserMeetupRelController {

    @RequestMapping("/meetup/{meetupId}/join")
    @PostMapping
    public void join (@AuthenticationPrincipal UserAccountPrincipal userAccountPrincipal){
        log.info("msg");
    }

}
