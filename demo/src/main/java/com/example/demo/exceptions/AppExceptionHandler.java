package com.example.demo.exceptions;

import jakarta.validation.ConstraintViolationException;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({StudentEmailMismatchException.class})
    public ProblemDetail handleEmailMismatch(StudentEmailMismatchException exception) {
        return ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage())
                ;

    }

    @ExceptionHandler(StudentIdMismatchException.class)
    public ProblemDetail handleIdMismatchException(StudentIdMismatchException exception) {
        return ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolationException(ConstraintViolationException exception) {
        return ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<Object>(new ApiError("unhandled error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }
}
