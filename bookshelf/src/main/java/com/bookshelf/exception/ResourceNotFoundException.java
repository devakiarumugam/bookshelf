package com.bookshelf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
@Setter
@RequiredArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private String resourceName;
  private Object value;

  public ResourceNotFoundException(String resourceName, Object value) {
    super(String.format("%s not found with : '%s'", resourceName, value));
    this.resourceName = resourceName;
    this.value = value;
  }
}
