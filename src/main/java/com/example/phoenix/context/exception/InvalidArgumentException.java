package com.example.phoenix.context.exception;


import com.example.phoenix.context.exception.handler.ErrorCode;
import com.example.phoenix.context.exception.handler.ExceptionInterface;

public class InvalidArgumentException extends RuntimeException implements ExceptionInterface {

  private ErrorCode code = ErrorCode.INVALID_ARGUMENT_ERROR_CODE;

  public InvalidArgumentException(ErrorCode code) {
    this.code = code;
  }

  public InvalidArgumentException(final String message) {
    super(message);
  }

  public InvalidArgumentException(final ErrorCode code, final String message) {
    super(message);
    this.code = code;
  }

  @Override
  public ErrorCode getCode() {
    return code;
  }
}
