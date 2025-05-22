package com.example.phoenix.context.exception;


import com.example.phoenix.context.exception.handler.ErrorCode;

public class InvalidTokenException extends UnauthorizedException {
  private final ErrorCode code = ErrorCode.INVALID_TOKEN_ERROR_CODE;

  @Override
  public ErrorCode getCode() {
    return code;
  }
}
