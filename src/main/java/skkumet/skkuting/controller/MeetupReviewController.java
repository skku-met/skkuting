package skkumet.skkuting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import skkumet.skkuting.dto.request.CreateMeetupReviewRequest;
import skkumet.skkuting.dto.response.CreateMeetupReviewResponse;
import skkumet.skkuting.service.MeetupReviewService;

@RestController
@RequestMapping("/review")
public class MeetupReviewController {
    @Autowired
    MeetupReviewService service;

    @PostMapping("")
    public ResponseEntity<CreateMeetupReviewResponse> createReview(
            @Valid @RequestBody CreateMeetupReviewRequest req) {
        return ResponseEntity.ok().body(
                CreateMeetupReviewResponse.fromEntity(service.createReview(req.toDto("test@g.skku.edu"))));
    }
}
