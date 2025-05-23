package com.example.phoenix.model.request;

import com.example.phoenix.context.exception.handler.ErrorCode;
import com.example.phoenix.context.exception.handler.Validator;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.immutables.builder.Builder;

public record OrderResponse(
    UUID id,
    List<CartItem> cartItems,
    String comment,
    Double totalPrice,
    Boolean isPaid,
    Instant createdDate) {}
