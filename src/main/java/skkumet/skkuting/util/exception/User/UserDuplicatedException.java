package skkumet.skkuting.util.exception.User;

import lombok.Getter;
import skkumet.skkuting.util.ErrorCode;

public class UserDuplicatedException extends RuntimeException {
    @Getter
    private final ErrorCode errorCode;
    public UserDuplicatedException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
