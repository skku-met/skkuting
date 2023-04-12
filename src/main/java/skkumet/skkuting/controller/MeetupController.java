package skkumet.skkuting.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import skkumet.skkuting.dto.MeetupDto;
import skkumet.skkuting.dto.UserAccountPrincipal;
import skkumet.skkuting.dto.request.CreateMeetupRequest;
import skkumet.skkuting.dto.response.CreateMeetupResponse;
import skkumet.skkuting.service.MeetupService;
import skkumet.skkuting.util.Response;
import skkumet.skkuting.util.SuccessResponse.SuccessCode;

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
    public Response<CreateMeetupResponse> postNewMeetup(
            @RequestBody @Valid CreateMeetupRequest meetupRequest,
            @AuthenticationPrincipal UserAccountPrincipal principal) {
        CreateMeetupResponse res = CreateMeetupResponse.fromDto(
                meetupService.createMeetup(meetupRequest.toDto(principal.email())));
        return new Response<CreateMeetupResponse>(res, SuccessCode.SUCCESS_CREATE);
    }

    @PostMapping("/update")
    public void updateMeetup(@RequestBody CreateMeetupRequest meetupRequest) {

    }

    @PostMapping("/{meetupId}/close_recruit")
    public void closeRecruit(@PathVariable Long meetupId, @AuthenticationPrincipal UserAccountPrincipal user) {
        meetupService.closeRecruit(meetupId, user.email());
    }
}
