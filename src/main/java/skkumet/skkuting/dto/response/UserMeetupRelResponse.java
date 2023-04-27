package skkumet.skkuting.dto.response;

import lombok.Builder;
import skkumet.skkuting.domain.UserMeetupRel;

import java.time.LocalDateTime;

@Builder
public record UserMeetupRelResponse(Long id, Boolean allowed, String userEmail, LocalDateTime createdAt, LocalDateTime modifiedAt,
                                    String createdBy, String modifiedBy) {

    public static UserMeetupRelResponse from(UserMeetupRel entity) {
        return UserMeetupRelResponse.builder()
                .id(entity.getId())
                .allowed(entity.getAllowed())
                .userEmail(entity.getUserAccount().getEmail())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .modifiedAt(entity.getModifiedAt())
                .modifiedBy(entity.getModifiedBy())
                .build();
    }
}