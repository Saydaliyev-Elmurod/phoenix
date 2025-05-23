package com.example.phoenix.model.response;

import java.time.LocalDate;

    public record TotalOrderStatistic(
    Integer totalPaidCount,
    Integer totalPaidPrice,
    Integer totalNotPaidCount,
    Integer totalNotPaidPrice) {}
