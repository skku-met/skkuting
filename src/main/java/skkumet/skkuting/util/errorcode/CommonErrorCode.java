package skkumet.skkuting.util.errorcode;

import lombok.Getter;

@Getter
public enum CommonErrorCode {
    INVALID_INPUT_VALUE(400, "001", "유효성 검증에 실패하였습니다."),
    INTERNAL_SERVER_ERROR(500, "002", "서버에 문제가 있습니다."),
    ;

    private final String codePrefix = "COMMON-";
    private Integer httpStatus;
    private String code;
    private String description;

    CommonErrorCode(Integer status, String code, String description) {
        this.httpStatus = status;
        this.code = this.codePrefix + code;
        this.description = description;
    }
}
