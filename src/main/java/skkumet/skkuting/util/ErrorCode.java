package skkumet.skkuting.util;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ErrorCode {

    // 추후 도메인별로 에러코드 범위를 나눌필요가 있음.

    INVALID_INPUT_VALUE(400, "COMMON-001", "유효성 검증에 실패하였습니다."),
    INTERNAL_SERVER_ERROR(500, "COMMON-002", "서버에 문제가 있습니다."),

    DUPLICATE_LOGIN_ID(400, "ACCOUNT-001", "이미 존재하는 회원입니다."),
    UNAUTHORIZED(401, "ACCOUNT-002", "인증이 되지 않았습니다."),
    ACCOUNT_NOT_FOUND(404, "ACCOUNT-003", "해당 계정은 존재하지 않습니다."),
    TOKEN_NOT_EXISTS(404, "ACCOUNT-004", "해당 토큰은 존재하지 않습니다.")
    ;

    private Integer status;
    private String code;
    private String description;

    ErrorCode(Integer status, String code, String description) {
        this.status = status;
        this.code = code;
        this.description = description;
    }
}
