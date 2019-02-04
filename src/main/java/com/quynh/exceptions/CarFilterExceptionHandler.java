package com.quynh.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

/**
 * Exception handlers
 */
@ControllerAdvice
public class CarFilterExceptionHandler
    extends ResponseEntityExceptionHandler {

    /**
     * Constraint violation exceptions handlers
     * @param ex Exception details
     * @param request HTTP request details
     * @return HTTP response of 400 bad request
     */
    @ExceptionHandler(value
        = {ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintViolation(
        RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Request contains invalid parameter or value";
        return handleExceptionInternal(ex, bodyOfResponse,
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}