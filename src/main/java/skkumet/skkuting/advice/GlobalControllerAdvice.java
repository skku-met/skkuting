package skkumet.skkuting.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import skkumet.skkuting.util.ErrorResponse;
import skkumet.skkuting.util.exception.User.UserDuplicatedException;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(UserDuplicatedException.class)
    public ErrorResponse handleUserDuplicatedException(UserDuplicatedException e) {
        return ErrorResponse.of(e.getErrorCode());
    }
}
