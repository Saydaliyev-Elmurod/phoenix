package com.example.phoenix.context.exception;


import com.example.phoenix.context.exception.handler.ErrorCode;
import com.example.phoenix.context.exception.handler.ExceptionInterface;

public class TimeoutException extends RuntimeException implements ExceptionInterface {
  private final ErrorCode code = ErrorCode.TIMEOUT_ERROR_CODE;

  public TimeoutException() {}

  public TimeoutException(final String message) {
    super(message);
  }

  public TimeoutException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public TimeoutException(final Throwable cause) {
    super(cause);
  }

  @Override
  public ErrorCode getCode() {
    return code;
  }
}
