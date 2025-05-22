package com.example.phoenix.context.exception;


import com.example.phoenix.context.exception.handler.ErrorCode;
import com.example.phoenix.context.exception.handler.ExceptionInterface;

public class JsonParsingException extends RuntimeException implements ExceptionInterface {
  private ErrorCode code = ErrorCode.JSON_PARSING_ERROR_CODE;

  public JsonParsingException() {}

  public JsonParsingException(final String message) {
    super(message);
  }

  public JsonParsingException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public JsonParsingException(final ErrorCode errorCode, final String message) {
    super(message);
    this.code = errorCode;
  }

  public JsonParsingException(final Throwable cause) {
    super(cause);
  }

  @Override
  public ErrorCode getCode() {
    return code;
  }
}
