package skkumet.skkuting.dto.constant;

import lombok.Getter;

public enum MeetupStatus {
    OPEN("모집중"), CLOSE("모집마감"), END("모임종료");

    @Getter
    private final String description;

    MeetupStatus(String description) {
        this.description = description;
    }
}
