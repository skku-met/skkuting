package skkumet.skkuting.util.errorcode;

import lombok.Getter;

@Getter
public enum AccountErrorCode implements ErrorCode {
    DUPLICATE_LOGIN_ID(400, "001", "이미 존재하는 회원입니다."),
    INCORRECT_PASSWORD_PASSWORDCONF(404, "002", "비밀번호와 비밀번호확인이 일치하지 않습니다."),
    UNAUTHORIZED(401, "003", "인증이 되지 않았습니다."),
    ACCOUNT_NOT_FOUND(404, "004", "해당 계정은 존재하지 않습니다."),
    TOKEN_NOT_EXISTS(404, "005", "해당 토큰은 존재하지 않습니다."),
    ;

    private final String codePrefix = "ACCOUNT-";
    private Integer httpStatus;
    private String code;
    private String description;

    AccountErrorCode(Integer status, String code, String description) {
        this.httpStatus = status;
        this.code = this.codePrefix + code;
        this.description = description;
    }
}
