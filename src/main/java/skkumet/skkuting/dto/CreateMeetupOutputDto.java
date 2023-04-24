package skkumet.skkuting.dto;

import lombok.Builder;
import skkumet.skkuting.domain.Meetup;
import skkumet.skkuting.dto.response.CreateMeetupResponse;

@Builder
public record CreateMeetupOutputDto(
        Long id) {

    public CreateMeetupResponse toRes() {
        return CreateMeetupResponse.builder().id(id).build();
    }

    public static CreateMeetupOutputDto fromEntity(Meetup meetup) {
        return CreateMeetupOutputDto.builder().id(meetup.getId()).build();
    }
}
