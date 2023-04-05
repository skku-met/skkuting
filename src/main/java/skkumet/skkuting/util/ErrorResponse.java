package skkumet.skkuting.util;

import lombok.Builder;
import lombok.Data;

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
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .description(errorCode.getDescription())
                .timestamp(LocalDateTime.now())
                .build();
    }

}
