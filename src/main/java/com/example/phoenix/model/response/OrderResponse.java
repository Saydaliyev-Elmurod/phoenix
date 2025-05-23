package com.example.phoenix.model.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderResponse(
    UUID id,
    List<CartItem> cartItems,
    String comment,
    Double totalPrice,
    Boolean isPaid,
    Instant createdDate) {}
