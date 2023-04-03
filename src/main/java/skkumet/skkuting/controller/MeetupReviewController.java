package skkumet.skkuting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import skkumet.skkuting.dto.CreateMeetupReviewDto;
import skkumet.skkuting.dto.request.MeetupReviewRequest;
import skkumet.skkuting.service.MeetupReviewService;

@RestController
@RequestMapping("/review")
public class MeetupReviewController {
    @Autowired
    MeetupReviewService service;

    @PostMapping("")
    public CreateMeetupReviewDto.Success createReview(
        MeetupReviewRequest.Input req
    ) {
        try {
            return service.createReview(req.toDto("test@g.skku.edu"));
        } catch (CreateMeetupReviewDto.Failed e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), e.getMessage());
        }
    }
}
