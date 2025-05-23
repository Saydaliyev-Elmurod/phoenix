package com.example.phoenix.model.request;

import com.example.phoenix.context.exception.handler.ErrorCode;
import com.example.phoenix.context.exception.handler.Validator;
import com.example.phoenix.util.Util;
import org.immutables.builder.Builder;

public record LoginRequest(String phone, String password) {
  @Builder.Constructor
  public LoginRequest {
    Validator.isTrue(ErrorCode.REQUIRED_FIELD_MISSED, phone != null, "phone required");
    Validator.isTrue(ErrorCode.REQUIRED_FIELD_MISSED, password != null, "password required");
    Validator.isTrue(
        ErrorCode.REQUIRED_FIELD_MISSED, Util.validateUzPhoneNumber(phone), "invalid phone");
  }
}
