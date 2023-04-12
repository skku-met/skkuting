package skkumet.skkuting.dto.request;

import java.time.LocalDateTime;

import org.springframework.boot.context.properties.bind.DefaultValue;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import skkumet.skkuting.dto.CreateMeetupInputDto;
import skkumet.skkuting.dto.constant.AuthorizingPolicy;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
public class CreateMeetupRequest {
    @NotBlank
    String title;

    @Builder.Default
    String content = "";
    Integer maxMember;

    @Builder.Default
    Integer minMember = 0;
    @NotNull
    LocalDateTime startDate;
    @NotNull
    String duration;
    String place;
    @NotNull
    AuthorizingPolicy authorizingPolicy;

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
