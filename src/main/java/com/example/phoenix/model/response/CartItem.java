package com.example.phoenix.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public record CartItem(@JsonIgnore UUID orderId, BookResponse book, Integer count, Double price) {
}
