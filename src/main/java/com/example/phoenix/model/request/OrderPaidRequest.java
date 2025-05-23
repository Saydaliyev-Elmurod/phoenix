package com.example.phoenix.model.request;

import com.example.phoenix.context.exception.handler.ErrorCode;
import com.example.phoenix.context.exception.handler.Validator;
import jakarta.validation.Valid;
import org.immutables.builder.Builder;

import java.util.UUID;

public record OrderPaidRequest(UUID orderId, String cardNumber) {

  @Builder.Constructor
  public OrderPaidRequest {
    Validator.notNull(ErrorCode.REQUIRED_FIELD_MISSED, orderId, "order required");
    Validator.notNull(ErrorCode.REQUIRED_FIELD_MISSED, cardNumber, "card required");
    Validator.isTrue(
        ErrorCode.INVALID_CARD_NUMBER, cardNumber.length() == 16, "invalid card number");
    Validator.isTrue(
        ErrorCode.INVALID_CARD_NUMBER,
        cardNumber.chars().allMatch(Character::isDigit),
        "invalid card number");
  }
}
