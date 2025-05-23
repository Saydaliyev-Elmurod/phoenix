package com.example.phoenix.model.request;

import com.example.phoenix.context.exception.handler.ErrorCode;
import com.example.phoenix.context.exception.handler.Validator;
import org.immutables.builder.Builder;

import java.util.List;
import java.util.UUID;

public record OrderRequest(
    List<OrderBookRequest> books,
    Double totalPrice,
    String comment) {
  @Builder.Constructor
  public OrderRequest {
    Validator.notNull(ErrorCode.REQUIRED_FIELD_MISSED, books, "books required");
    Validator.isTrue(ErrorCode.REQUIRED_FIELD_MISSED, !books.isEmpty(), "books required");
    Validator.notNull(ErrorCode.REQUIRED_FIELD_MISSED, totalPrice, "price required");
  }
}
