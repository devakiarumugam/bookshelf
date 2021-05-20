package com.bookshelf.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Object> handleUncaughtErrors(Exception ex) {
    return new ResponseEntity<>(
        "Server error, contact administrator", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
