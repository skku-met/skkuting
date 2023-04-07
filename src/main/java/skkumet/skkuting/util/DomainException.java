package skkumet.skkuting.util;

import lombok.Getter;
import skkumet.skkuting.util.errorcode.ErrorCode;

public class DomainException extends RuntimeException {
    @Getter
    private final ErrorCode errorCode;

    public DomainException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
