package com.example.firstproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerNotFoundException (NotFoundException ex, WebRequest req) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(ErrorCreateBecauseEntityExistInLisException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handlerErrorCreateBecauseEntityExistInList(ErrorCreateBecauseEntityExistInLisException ex, WebRequest req){
        return new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
    }
}
