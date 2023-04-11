package skkumet.skkuting.util.errorcode;

import lombok.Getter;

@Getter
public enum MeetupErrorCode implements ErrorCode {
    MEETUP_NOT_EXIST(404, "001", "존재하지 않는 모임입니다."),
    NOT_MEETUP_HOST(400, "002", "모임의 호스트가 아닙니다."),
    ;

    private final String codePrefix = "MEETUP-";
    private Integer httpStatus;
    private String code;
    private String description;

    MeetupErrorCode(Integer status, String code, String description) {
        this.httpStatus = status;
        this.code = this.codePrefix + code;
        this.description = description;
    }

}
