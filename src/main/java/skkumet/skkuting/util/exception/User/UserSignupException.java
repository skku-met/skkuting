package skkumet.skkuting.util.exception.User;

import lombok.Getter;
import skkumet.skkuting.util.ErrorCode;

public class UserSignupException extends RuntimeException {
    @Getter
    private final ErrorCode errorCode;
    public UserSignupException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
