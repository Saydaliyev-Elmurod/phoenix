package com.example.phoenix.model;

import java.util.UUID;

public record UserPrincipal(UserResponse user, String token) {}
