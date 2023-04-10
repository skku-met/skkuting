package skkumet.skkuting.advice;

import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import skkumet.skkuting.util.DomainException;
import skkumet.skkuting.util.ErrorResponse;
import skkumet.skkuting.util.errorcode.CommonErrorCode;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleUserDuplicatedException(DomainException e) {
        ErrorResponse errorRes = ErrorResponse.of(e.getErrorCode());
        return ResponseEntity.status(errorRes.getStatus()).body(errorRes);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorDescription = e.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(e.getStatusCode()).body(ErrorResponse.of(
                e.getStatusCode().value(),
                CommonErrorCode.INVALID_INPUT_VALUE.getCode(),
                errorDescription));
    }

}
