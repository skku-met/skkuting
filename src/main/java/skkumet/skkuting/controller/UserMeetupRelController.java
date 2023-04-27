package skkumet.skkuting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import skkumet.skkuting.dto.UserAccountDto;
import skkumet.skkuting.dto.UserAccountPrincipal;
import skkumet.skkuting.dto.UserMeetupRelDto;
import skkumet.skkuting.dto.response.UserMeetupRelResponse;
import skkumet.skkuting.service.UserMeetupRelService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserMeetupRelController {

    private final UserMeetupRelService userMeetupRelService;

    @PostMapping("/meetup/{meetupId}/join")
    public ResponseEntity<UserMeetupRelResponse> join (@AuthenticationPrincipal UserAccountPrincipal userAccountPrincipal,
                                                       @PathVariable Long meetupId){
        return userMeetupRelService.join(UserAccountDto.from(userAccountPrincipal), meetupId);
    }

    @PostMapping("/meetup/{meetupId}/accept/{userEmail}")
    public ResponseEntity<UserMeetupRelDto> accept(@AuthenticationPrincipal UserAccountPrincipal userAccountPrincipal,
                                                   @PathVariable Long meetupId,
                                                   @PathVariable String userEmail) {
        return userMeetupRelService.acceptjoinedUser(UserAccountDto.from(userAccountPrincipal),meetupId,userEmail);
    }

    @PostMapping("/meetup/{meetupId}/cancel/{userEmail}")
    public void cancel (@AuthenticationPrincipal UserAccountPrincipal userAccountPrincipal,
                        @PathVariable Long meetupId,
                        @PathVariable String userEmail) {
        userMeetupRelService.cancelJoin(UserAccountDto.from(userAccountPrincipal),meetupId,userEmail);

    }
}
