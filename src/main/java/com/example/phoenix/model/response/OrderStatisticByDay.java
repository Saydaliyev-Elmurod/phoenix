package com.example.phoenix.model.response;

import java.time.Instant;
import java.time.LocalDate;

public record OrderStatisticByDay(LocalDate day, Double totalPrice, Integer count) {}
