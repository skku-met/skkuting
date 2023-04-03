package skkumet.skkuting.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import skkumet.skkuting.dto.CreateMeetupReviewDto;

public class MeetupReviewRequest {
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Input {
        Long meetupId;
        String reviewSenderId;

        int rating;
        String content;

        public CreateMeetupReviewDto.Input toDto(String userId) {
            return CreateMeetupReviewDto.Input.builder()
                    .reviewRecipientId(userId)
                    .reviewSenderId(this.reviewSenderId)
                    .rating(this.rating)
                    .content(this.content)
                    .build();
        }
    }
}
