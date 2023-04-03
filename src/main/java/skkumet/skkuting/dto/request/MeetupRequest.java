package skkumet.skkuting.dto.request;

import skkumet.skkuting.domain.UserAccount;
import skkumet.skkuting.dto.MeetupDto;
import skkumet.skkuting.dto.constant.AuthorizingPolicy;
import skkumet.skkuting.dto.constant.MeetupStatus;

import java.time.LocalDateTime;

public record MeetupRequest(
        String title,
        String content,
        Integer max_member,
        Integer min_member,
        LocalDateTime due_date,
        LocalDateTime start_date,
        String duration,
        String place,
        AuthorizingPolicy authorizingPolicy,
        MeetupStatus meetupStatus
) {
    public static MeetupRequest of(String title, String content, Integer max_member, Integer min_member, LocalDateTime due_date, LocalDateTime start_date, String duration, String place, AuthorizingPolicy authorizingPolicy, MeetupStatus meetupStatus) {
        return new MeetupRequest(title, content, max_member, min_member, due_date, start_date, duration, place, authorizingPolicy, meetupStatus);
    }

    public MeetupDto toDto(UserAccount userAccount) {
        return MeetupDto.of(
                title,
                content,
                max_member,
                min_member,
                due_date,
                start_date,
                duration,
                place,
                authorizingPolicy,
                meetupStatus,
                userAccount
        );
    }
}