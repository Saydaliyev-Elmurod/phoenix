package com.example.phoenix.context.exception;


import com.example.phoenix.context.exception.handler.ErrorCode;
import com.example.phoenix.context.exception.handler.ExceptionInterface;

public class InternalException extends RuntimeException implements ExceptionInterface {
  private final ErrorCode code = ErrorCode.INTERNAL_ERROR_CODE;

  public InternalException() {}

  public InternalException(final String message) {
    super(message);
  }

  public InternalException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public InternalException(final Throwable cause) {
    super(cause);
  }

  public InternalException(
      final String message,
      final Throwable cause,
      final boolean enableSuppression,
      final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  @Override
  public ErrorCode getCode() {
    return code;
  }
}
