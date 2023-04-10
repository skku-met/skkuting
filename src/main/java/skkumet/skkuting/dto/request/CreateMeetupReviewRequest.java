package skkumet.skkuting.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import skkumet.skkuting.dto.CreateMeetupReviewInputDto;

public record CreateMeetupReviewRequest(
        @NotNull Long meetupId,
        @NotNull @Email String reviewRecipientId,
        @NotNull @Min(1) @Max(5) int rating,
        @NotBlank String content) {

    public CreateMeetupReviewRequest(
            Long meetupId,
            String reviewRecipientId,
            int rating,
            String content) {
        this.meetupId = meetupId;
        this.reviewRecipientId = reviewRecipientId;
        this.rating = rating;
        this.content = content;
    }

    public CreateMeetupReviewInputDto toDto(String userId) {
        return CreateMeetupReviewInputDto.builder()
                .meetupId(this.meetupId)
                .reviewRecipientId(this.reviewRecipientId)
                .reviewSenderId(userId)
                .rating(this.rating)
                .content(this.content)
                .build();
    }
}
