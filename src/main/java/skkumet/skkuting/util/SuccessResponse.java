package skkumet.skkuting.util;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class SuccessResponse<T> {
    private HttpStatus status;
    private T content;
    private String description;

    public SuccessResponse(SuccessCode code, T content) {
        this.status = code.status;
        this.content = content;
        this.description = code.description;
    }

    @Getter
    public static enum SuccessCode {
        SUCCESS_RETREIVE(200, "조회에 성공했습니다."),
        SUCCESS_CREATE(201, "생성에 성공했습니다."),;

        private HttpStatus status;
        private String description;

        SuccessCode(HttpStatus status, String description) {
            this.status = status;
            this.description = description;
        }

        SuccessCode(int status, String description) {
            this(HttpStatus.valueOf(status), description);
        }
    }
}
