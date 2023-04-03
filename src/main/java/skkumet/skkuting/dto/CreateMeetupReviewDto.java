package skkumet.skkuting.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import skkumet.skkuting.domain.MeetupReview;

public class CreateMeetupReviewDto {

    @Builder
    public static record InputT (

        Long meetupId,
        String reviewRecipientId,
        String reviewSenderId,
        int rating,
        String content
    ) {

    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Input {
        Long meetupId;
        String reviewRecipientId;
        String reviewSenderId;
        int rating;
        String content;

        public MeetupReview toEntity() {
            return MeetupReview.builder()
                .id(this.getMeetupId())
                .reviewRecipientId(this.reviewRecipientId)
                .reviewSenderId(this.reviewSenderId)
                .rating(this.rating)
                .content(this.content)
                .build();
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Success {
        Long meetupReviewId;

        public static Success fromEntity(MeetupReview review) {
            return Success.builder()
                .meetupReviewId(review.getId())
                .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    public static class Failed extends RuntimeException {
        private String message;
    }

}