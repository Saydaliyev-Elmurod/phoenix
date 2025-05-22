package com.example.phoenix.context.exception;


import com.example.phoenix.context.exception.handler.ErrorCode;
import com.example.phoenix.context.exception.handler.ExceptionInterface;

public class RetrieveForbiddenException extends ForbiddenException implements ExceptionInterface {
  private final ErrorCode code = ErrorCode.RETRIEVE_FORBIDDEN_ERROR_CODE;

  public RetrieveForbiddenException() {}

  public RetrieveForbiddenException(final String message) {
    super(message);
  }

  public RetrieveForbiddenException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public RetrieveForbiddenException(final Throwable cause) {
    super(cause);
  }

  @Override
  public ErrorCode getCode() {
    return code;
  }
}
