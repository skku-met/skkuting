package skkumet.skkuting.util;

import lombok.Builder;
import lombok.Data;
import skkumet.skkuting.util.errorcode.ErrorCode;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    private Integer status;
    private String code;
    private String description;
    private LocalDateTime timestamp;

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponseBuilder()
                .status(errorCode.getHttpStatus())
                .code(errorCode.getCode())
                .description(errorCode.getDescription())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ErrorResponse of(Integer status, String code, String description) {
        return new ErrorResponseBuilder()
                .status(status)
                .code(code)
                .description(description)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
