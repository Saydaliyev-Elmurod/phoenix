package com.example.phoenix.model.response;

import java.time.Instant;
import java.util.UUID;

public record BookResponse(
    UUID id,
    String image,
    String name,
    Double price,
    String description,
    Instant createdDate,
    Instant lastModifiedDate) {}
