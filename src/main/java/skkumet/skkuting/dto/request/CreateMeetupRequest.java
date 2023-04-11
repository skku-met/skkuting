package skkumet.skkuting.dto.request;

import java.time.LocalDateTime;

import org.springframework.boot.context.properties.bind.DefaultValue;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import skkumet.skkuting.dto.CreateMeetupInputDto;
import skkumet.skkuting.dto.constant.AuthorizingPolicy;

public record CreateMeetupRequest(
        @NotBlank String title,
        @DefaultValue("") String content,
        Integer maxMember,
        @DefaultValue("0") Integer minMember,
        @NotNull LocalDateTime startDate,
        @NotNull String duration,
        String place,
        @NotNull AuthorizingPolicy authorizingPolicy) {

    public CreateMeetupInputDto toDto(String userId) {
        return CreateMeetupInputDto.builder()
                .title(title)
                .content(content)
                .maxMember(maxMember)
                .minMember(minMember)
                .startDate(startDate)
                .duration(duration)
                .place(place)
                .authorizingPolicy(authorizingPolicy)
                .host(userId)
                .build();
    }
}
