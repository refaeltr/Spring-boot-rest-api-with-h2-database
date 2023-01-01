package com.example.demo.exceptions;

import lombok.NoArgsConstructor;

//@NoArgsConstructor
public class StudentEmailMismatchException extends RuntimeException {
    public StudentEmailMismatchException() {
        super("my error: email mismatch");
    }
}
