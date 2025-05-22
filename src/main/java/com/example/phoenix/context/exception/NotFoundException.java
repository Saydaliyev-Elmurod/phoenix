package com.example.phoenix.context.exception;

import java.util.NoSuchElementException;

import com.example.phoenix.context.exception.handler.ErrorCode;
import com.example.phoenix.context.exception.handler.ExceptionInterface;
import com.example.phoenix.context.exception.handler.ExceptionResponse;
import lombok.Getter;

public class NotFoundException extends NoSuchElementException implements ExceptionInterface {
  private ErrorCode code = ErrorCode.NOT_FOUND_ERROR_CODE;
  @Getter private ExceptionResponse exceptionResponse;

  public NotFoundException(ErrorCode code) {
    super();
    this.code = code;
  }

  public NotFoundException(ExceptionResponse exceptionResponse) {
    super();
    this.exceptionResponse = exceptionResponse;
  }

  @Override
  public ErrorCode getCode() {
    return code;
  }

  public NotFoundException(final String message) {
    super(message);
  }

  public NotFoundException(final ErrorCode code, final String message) {
    super(message);
    this.code = code;
  }
}
