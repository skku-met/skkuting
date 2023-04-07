package skkumet.skkuting.advice;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import skkumet.skkuting.util.ErrorCode;
import skkumet.skkuting.util.ErrorResponse;
import skkumet.skkuting.util.exception.User.UserSignupException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(UserSignupException.class)
    public ErrorResponse handleUserDuplicatedException(UserSignupException e) {
        return ErrorResponse.of(e.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorDescription = e.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return ErrorResponse.of(
                e.getStatusCode().value(),
                ErrorCode.INVALID_INPUT_VALUE.getCode(),
                errorDescription
        );
    }



}
