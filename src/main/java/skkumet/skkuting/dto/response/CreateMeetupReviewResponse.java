package skkumet.skkuting.dto.response;

import lombok.Builder;
import skkumet.skkuting.domain.MeetupReview;

@Builder
public record CreateMeetupReviewResponse(
        Long meetupReviewId) {
    public static CreateMeetupReviewResponse fromEntity(MeetupReview meetupReview) {
        return new CreateMeetupReviewResponse(meetupReview.getId());
    }
}
