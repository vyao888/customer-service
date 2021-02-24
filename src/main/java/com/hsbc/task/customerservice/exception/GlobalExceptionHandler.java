package com.hsbc.task.customerservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  public final static String NOT_FOUND = "Failed to find the resource";
  public final static String BAD_REQUEST = "Invalid input";
  public final static String UNEXPECTED = "Unexpected error";

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    log.error(NOT_FOUND, ex);
    return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
  public ResponseEntity<?> invalidOperationException(IllegalArgumentException ex, WebRequest request) {
    log.error(BAD_REQUEST, ex);
    return new ResponseEntity<>(BAD_REQUEST, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> exceptionHandler(Exception ex, WebRequest request) {
    log.error(UNEXPECTED, ex);
    return new ResponseEntity<>(UNEXPECTED, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
