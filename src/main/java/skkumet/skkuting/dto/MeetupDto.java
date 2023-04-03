package skkumet.skkuting.dto;

import skkumet.skkuting.domain.Meetup;
import skkumet.skkuting.domain.MeetupReview;
import skkumet.skkuting.domain.UserAccount;
import skkumet.skkuting.domain.UserMeetupRel;
import skkumet.skkuting.dto.constant.AuthorizingPolicy;
import skkumet.skkuting.dto.constant.MeetupStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record MeetupDto(
        Long id,
        String title,
        String content,
        Integer max_member,
        Integer min_member,
        LocalDateTime due_date,
        LocalDateTime start_date,
        String duration,
        String place,
        AuthorizingPolicy authorizingPolicy,
        MeetupStatus meetupStatus,
        UserAccount host,
        List<UserMeetupRel> userJoinedList,
        List<MeetupReview> meetupReviewList,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
    public static MeetupDto of(Long id, String title, String content, Integer max_member, Integer min_member, LocalDateTime due_date, LocalDateTime start_date, String duration, String place, AuthorizingPolicy authorizingPolicy, MeetupStatus meetupStatus, UserAccount host, List<UserMeetupRel> userJoinedList, List<MeetupReview> meetupReviewList, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy) {
        return new MeetupDto(id, title, content, max_member, min_member, due_date, start_date, duration, place, authorizingPolicy, meetupStatus, host, userJoinedList, meetupReviewList, createdAt, modifiedAt, createdBy, modifiedBy);
    }

    public static MeetupDto of (String title,
                                String content,
                                Integer max_member,
                                Integer min_member,
                                LocalDateTime due_date,
                                LocalDateTime start_date,
                                String duration,
                                String place,
                                AuthorizingPolicy authorizingPolicy,
                                MeetupStatus meetupStatus,
                                UserAccount host){
        return MeetupDto.of(
                null,
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
                host,
                null,
                null,
                null,null,null,null);
    }

    public Meetup toEntity() {
        return Meetup.of(
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
                host
        );
    }
}