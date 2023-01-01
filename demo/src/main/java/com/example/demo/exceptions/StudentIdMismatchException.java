package com.example.demo.exceptions;


import lombok.NoArgsConstructor;

public class StudentIdMismatchException extends RuntimeException {
    public StudentIdMismatchException() {
        super("my error: id mismatch");
    }
}
