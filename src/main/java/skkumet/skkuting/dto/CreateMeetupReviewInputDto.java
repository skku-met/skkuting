package skkumet.skkuting.dto;

import lombok.Builder;
import skkumet.skkuting.domain.MeetupReview;
import skkumet.skkuting.domain.UserAccount;

@Builder
public record CreateMeetupReviewInputDto(
        Long meetupId,
        String reviewRecipientId,
        String reviewSenderId,
        int rating,
        String content) {

    public MeetupReview toEntity() {
        return MeetupReview.builder()
                .id(this.meetupId)
                .reviewRecipient(UserAccount.of(this.reviewRecipientId))
                .reviewSender(UserAccount.of(this.reviewSenderId))
                .rating(this.rating)
                .content(this.content)
                .build();
    }
}
