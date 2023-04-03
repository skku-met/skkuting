package skkumet.skkuting.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record UserMeetupRelDto(
        Long id,
        UserAccountDto userAccount,
        MeetupDto meetup,
        boolean isAllowed,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
}