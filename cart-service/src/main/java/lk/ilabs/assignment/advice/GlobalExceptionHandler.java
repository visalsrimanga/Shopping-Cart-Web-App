package lk.ilabs.assignment.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public void handleHttpClientNotFoundException(HttpClientErrorException exp){
        exp.initCause(new ResponseStatusException(HttpStatus.NOT_FOUND));
        throw exp;
    }
}
