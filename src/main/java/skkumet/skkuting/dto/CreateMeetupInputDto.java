package skkumet.skkuting.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import skkumet.skkuting.domain.Meetup;
import skkumet.skkuting.domain.UserAccount;
import skkumet.skkuting.dto.constant.AuthorizingPolicy;

@Builder
public record CreateMeetupInputDto(
        String title,
        String content,
        Integer maxMember,
        Integer minMember,
        LocalDateTime startDate,
        String duration,
        String place,
        String host,
        AuthorizingPolicy authorizingPolicy) {

    public Meetup toEntity() {
        return Meetup.buildWithHost(UserAccount.of(host))
                .title(title)
                .content(content)
                .maxMember(maxMember)
                .minMember(minMember)
                .startDate(startDate)
                .duration(duration)
                .place(place)
                .authorizingPolicy(authorizingPolicy)
                .build();
    }
}
