package skkumet.skkuting.dto;

import java.time.LocalDateTime;
import skkumet.skkuting.domain.Meetup;
import skkumet.skkuting.dto.constant.AuthorizingPolicy;
import skkumet.skkuting.dto.constant.MeetupStatus;

public record MeetupDto(Long id, String title, String content, Integer max_member,
                Integer min_member, LocalDateTime due_date, LocalDateTime start_date,
                String duration, String place, AuthorizingPolicy authorizingPolicy,
                MeetupStatus meetupStatus, UserAccountDto host, LocalDateTime createdAt,
                LocalDateTime modifiedAt, String createdBy, String modifiedBy) {
        public static MeetupDto of(Long id, String title, String content, Integer max_member,
                        Integer min_member, LocalDateTime due_date, LocalDateTime start_date,
                        String duration, String place, AuthorizingPolicy authorizingPolicy,
                        MeetupStatus meetupStatus, UserAccountDto host, LocalDateTime createdAt,
                        LocalDateTime modifiedAt, String createdBy, String modifiedBy) {
                return new MeetupDto(id, title, content, max_member, min_member, due_date,
                                start_date, duration, place, authorizingPolicy, meetupStatus, host,
                                createdAt, modifiedAt, createdBy, modifiedBy);
        }

        public static MeetupDto of(String title, String content, Integer max_member,
                        Integer min_member, LocalDateTime due_date, LocalDateTime start_date,
                        String duration, String place, AuthorizingPolicy authorizingPolicy,
                        MeetupStatus meetupStatus, UserAccountDto host) {
                return MeetupDto.of(null, title, content, max_member, min_member, due_date,
                                start_date, duration, place, authorizingPolicy, meetupStatus, host,
                                null, null, null, null);
        }

        public Meetup toEntity() {
                return Meetup.of(title, content, max_member, min_member, due_date, start_date,
                                duration, place, authorizingPolicy, meetupStatus,
                                UserAccountDto.toEntity(host));
        }

        public static MeetupDto from(Meetup meetup) {
                return new MeetupDto(meetup.getId(), meetup.getTitle(), meetup.getContent(),
                                meetup.getMax_member(), meetup.getMin_member(),
                                meetup.getDue_date(), meetup.getStart_date(), meetup.getDuration(),
                                meetup.getPlace(), meetup.getAuthorizingPolicy(),
                                meetup.getMeetupStatus(), UserAccountDto.from(meetup.getHost()),
                                meetup.getCreatedAt(), meetup.getModifiedAt(),
                                meetup.getCreatedBy(), meetup.getModifiedBy());
        }
}
