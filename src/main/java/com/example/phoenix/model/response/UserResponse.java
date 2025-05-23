package com.example.phoenix.model.response;

import java.time.Instant;
import java.util.UUID;

public record UserResponse(
    UUID id,
    String image,
    String firstName,
    String lastName,
    Instant birthday,
    String phone,
    String role,
    Instant createdDate) {}
