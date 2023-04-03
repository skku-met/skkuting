package skkumet.skkuting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import skkumet.skkuting.domain.UserAccount;
import skkumet.skkuting.dto.MeetupDto;
import skkumet.skkuting.dto.request.MeetupRequest;
import skkumet.skkuting.repository.UserAccountRepository;
import skkumet.skkuting.service.MeetupService;
import skkumet.skkuting.service.UserAccountService;

@RequiredArgsConstructor
@RequestMapping("/meetup")
@RestController
public class MeetupController {

    private final MeetupService meetupService;

    @GetMapping
    public Page<MeetupDto> meetUp(Pageable pageable){
        return meetupService.getMeetups(pageable);
    }

    @PostMapping
    public void postNewMeetup(@RequestBody MeetupRequest meetupRequest){
//        System.out.println(meetupRequest);
        meetupService.saveMeetup(meetupRequest);
    }

}
