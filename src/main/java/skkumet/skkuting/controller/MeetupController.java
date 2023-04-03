package skkumet.skkuting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import skkumet.skkuting.domain.UserAccount;
import skkumet.skkuting.dto.request.MeetupRequest;
import skkumet.skkuting.repository.UserAccountRepository;
import skkumet.skkuting.service.MeetupService;

@RequiredArgsConstructor
@RequestMapping("/meetup")
@RestController
public class MeetupController {

    private final MeetupService meetupService;
    private final UserAccountRepository userAccountRepository;

    @PostMapping
    public void postNewMeetup(@RequestBody MeetupRequest meetupRequest){
        UserAccount userAccount = userAccountRepository.findById("test@g.skku.edu").get();
        System.out.println(meetupRequest);
        meetupService.saveMeetup(meetupRequest.toDto(userAccount));
    }
}
