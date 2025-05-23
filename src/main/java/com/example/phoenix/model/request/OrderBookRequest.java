package com.example.phoenix.model.request;

import java.util.UUID;

public record OrderBookRequest(
        UUID bookId,
        Integer count
) {}
