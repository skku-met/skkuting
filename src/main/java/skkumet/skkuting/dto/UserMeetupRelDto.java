package skkumet.skkuting.dto;

import lombok.Builder;
import skkumet.skkuting.domain.Meetup;
import skkumet.skkuting.domain.UserMeetupRel;

import java.time.LocalDateTime;

@Builder
public record UserMeetupRelDto(Long id, UserAccountDto userAccountDto, MeetupDto meetupDto,
        Boolean allowed, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy,
        String modifiedBy) {
    public static UserMeetupRelDto of(String userEmail, Long meetupId, Boolean allowed) {
        return UserMeetupRelDto.builder()
                .meetupDto(MeetupDto.builder().id(meetupId).build())
                .userAccountDto(UserAccountDto.builder().email(userEmail).build())
                .allowed(allowed)
                .build();
    }

    public static UserMeetupRelDto from(UserMeetupRel entity) {
        return UserMeetupRelDto.builder()
                .id(entity.getId())
                .userAccountDto(UserAccountDto.from(entity.getUserAccount()))
                .meetupDto(MeetupDto.from(entity.getMeetup()))
                .allowed(entity.getAllowed())
                .createdAt(entity.getCreatedAt())
                .modifiedAt(entity.getModifiedAt())
                .createdBy(entity.getCreatedBy())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }

    public UserMeetupRel toEntity() {
        return UserMeetupRel.builder()
                .meetup(meetupDto.toEntity())
                .userAccount(userAccountDto.toEntity())
                .allowed(allowed)
                .build();
    }
}
