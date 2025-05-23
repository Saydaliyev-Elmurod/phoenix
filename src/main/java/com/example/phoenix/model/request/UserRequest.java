package com.example.phoenix.model.request;

import com.example.phoenix.context.exception.handler.ErrorCode;
import com.example.phoenix.context.exception.handler.Validator;
import com.example.phoenix.util.Util;
import org.immutables.builder.Builder;

import java.time.Instant;

public record UserRequest(
    String image,
    String firstName,
    String lastName,
    Instant birthday,
    String phone,
    String password) {
  @Builder.Constructor
  public UserRequest {
    Validator.isTrue(
        ErrorCode.REQUIRED_FIELD_MISSED,
        firstName != null && !firstName.isBlank(),
        "firstName required");
    Validator.isTrue(ErrorCode.REQUIRED_FIELD_MISSED, phone != null, "phone required");
    Validator.isTrue(
        ErrorCode.REQUIRED_FIELD_MISSED, Util.validateUzPhoneNumber(phone), "invalid phone");
  }
}
