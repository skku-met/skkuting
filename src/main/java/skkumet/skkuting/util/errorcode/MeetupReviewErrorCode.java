package skkumet.skkuting.util.errorcode;

import lombok.Getter;

@Getter
public enum MeetupReviewErrorCode implements ErrorCode {

    SELF_REVIEW_NOT_ALLOWED(400, "001", "자기 자신은 리뷰할 수 없습니다."),
    MEETUP_NOT_EXIST(400, "002", "대상 모임이 존재하지 않습니다."),
    USER_NOT_JOINED_MEETUP(400, "003", "사용자가 모임에 참여하지 않았습니다."),
    RECIPIENT_NOT_JOINED_MEETUP(400, "004", "리뷰 대상에 모임에 참여하지 않았습니다."),
    ALREADY_CREATED_REVIEW(409, "005", "이미 리뷰를 작성했습니다."),
    ;

    private final String codePrefix = "REVIEW_MEETUP-";
    private Integer httpStatus;
    private String code;
    private String description;

    MeetupReviewErrorCode(Integer status, String code, String description) {
        this.httpStatus = status;
        this.code = this.codePrefix + code;
        this.description = description;
    }
}
