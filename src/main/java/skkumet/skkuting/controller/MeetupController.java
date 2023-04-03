package skkumet.skkuting.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import skkumet.skkuting.dto.MeetupDto;
import skkumet.skkuting.dto.request.MeetupRequest;
import skkumet.skkuting.service.MeetupService;

@RequiredArgsConstructor
@RequestMapping("/meetup")
@RestController
public class MeetupController {

    private final MeetupService meetupService;

    @GetMapping
    public Page<MeetupDto> meetUp(Pageable pageable) {
        return meetupService.getMeetups(pageable);
    }

    @PostMapping
    public void postNewMeetup(@RequestBody MeetupRequest meetupRequest) {
        meetupService.saveMeetup(meetupRequest);
    }

    @PostMapping("/update")
    public void updateMeetup(@RequestBody MeetupRequest meetupRequest) {

    }


}
