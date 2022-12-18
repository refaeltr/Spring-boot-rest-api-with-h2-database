package com.example.demo.exceptions;

public class StudentEmailMismatchException extends RuntimeException {
    public StudentEmailMismatchException() {
    }

    public StudentEmailMismatchException(String message) {
        super(message);
    }

    public StudentEmailMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentEmailMismatchException(Throwable cause) {
        super(cause);
    }
}
