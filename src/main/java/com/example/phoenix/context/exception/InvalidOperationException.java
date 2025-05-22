package com.example.phoenix.context.exception;


import com.example.phoenix.context.exception.handler.ErrorCode;
import com.example.phoenix.context.exception.handler.ExceptionInterface;

public class InvalidOperationException extends RuntimeException implements ExceptionInterface {

  private ErrorCode code = ErrorCode.INVALID_OPERATION_ERROR_CODE;

  public InvalidOperationException(final ErrorCode code) {
    super();
    this.code = code;
  }

  public InvalidOperationException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidOperationException(ErrorCode code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
  }

  public InvalidOperationException(final String message) {
    super(message);
  }

  public InvalidOperationException(final ErrorCode code, final String message) {
    super(message);
    this.code = code;
  }

  @Override
  public ErrorCode getCode() {
    return code;
  }
}
