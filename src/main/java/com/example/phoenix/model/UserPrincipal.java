package com.example.phoenix.model;

import com.example.phoenix.model.response.UserResponse;

import java.util.UUID;

public record UserPrincipal(UserResponse user, String token) {}
