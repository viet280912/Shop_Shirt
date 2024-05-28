package com.example.firstproject.exception;

public class ErrorCreateBecauseEntityExistInLisException extends RuntimeException {
    public ErrorCreateBecauseEntityExistInLisException() {
        super();
    }

    public ErrorCreateBecauseEntityExistInLisException(String message) {
        super(message);
    }

    public ErrorCreateBecauseEntityExistInLisException(String message, Throwable cause) {
        super(message, cause);
    }
}
