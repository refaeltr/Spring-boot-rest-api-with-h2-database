package com.example.demo.exceptions;

public class StudentIdMismatchException extends RuntimeException {
    public StudentIdMismatchException() {
        super();
    }

    public StudentIdMismatchException(String message) {
        super(message);
    }

    public StudentIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentIdMismatchException(Throwable cause) {
        super(cause);
    }

}
