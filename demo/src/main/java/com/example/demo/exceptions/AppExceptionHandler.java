package com.example.demo.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/*
The@ControllerAdvice annotation allows us to consolidate our multiple,
 scattered @ExceptionHandlers from before into a single, global error handling component.
 */
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    public AppExceptionHandler() {
        super();
    }

    @ExceptionHandler({StudentEmailMismatchException.class})
    protected ResponseEntity<Object> handleEmailMismatch(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "my error: this email is already taken",
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({StudentIdMismatchException.class})
    public ResponseEntity<Object> handleIdMismatchException(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "my error: id is not in the database",
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<Object>(new ApiError("my error: " + ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }
}
