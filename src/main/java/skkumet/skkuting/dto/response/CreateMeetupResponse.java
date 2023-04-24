package skkumet.skkuting.dto.response;

import lombok.Builder;
import skkumet.skkuting.dto.CreateMeetupOutputDto;

@Builder
public record CreateMeetupResponse(
        Long id) {

    public static CreateMeetupResponse fromDto(CreateMeetupOutputDto dto) {
        return CreateMeetupResponse.builder().id(dto.id()).build();
    }
}
