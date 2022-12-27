package com.example.demo.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
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
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request); // BAD_REQUEST --> 400
    }

    @ExceptionHandler({StudentIdMismatchException.class})
    public ResponseEntity<Object> handleIdMismatchException(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "my error: id is not in the database",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request); // NOT_FOUND --> 404
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolationException(){
        return ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, "email size validation error");
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<Object>(new ApiError("unhandled error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }
}
