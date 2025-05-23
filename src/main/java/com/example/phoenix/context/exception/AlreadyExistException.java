package com.example.phoenix.context.exception;

import com.example.phoenix.context.exception.handler.ErrorCode;
import com.example.phoenix.context.exception.handler.ExceptionInterface;
import com.example.phoenix.context.exception.handler.ExceptionResponse;
import lombok.Getter;

public class AlreadyExistException extends RuntimeException implements ExceptionInterface {
  private ErrorCode code = ErrorCode.ALREADY_EXISTS_ERROR_CODE;
  @Getter private ExceptionResponse exceptionResponse;

  public AlreadyExistException(ErrorCode code,String message) {
    super(message);
    this.code = code;
  }

  public AlreadyExistException(ExceptionResponse exceptionResponse) {
    super();
    this.exceptionResponse = exceptionResponse;
  }

  @Override
  public ErrorCode getCode() {
    return code;
  }
}
