package com.example.phoenix.model.request;

import com.example.phoenix.context.exception.handler.ErrorCode;
import com.example.phoenix.context.exception.handler.Validator;
import org.immutables.builder.Builder;

public record BookRequest(String image, String name, Double price, String description) {

  @Builder.Constructor
  public BookRequest {
    Validator.isTrue(ErrorCode.REQUIRED_FIELD_MISSED, name != null, "name required");
    Validator.isTrue(ErrorCode.REQUIRED_FIELD_MISSED, price != null, "price required");
  }
}
