package com.example.phoenix.model.request;

import com.example.phoenix.model.response.BookResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public record CartItem(@JsonIgnore UUID orderId, BookResponse book, Integer count) {}
