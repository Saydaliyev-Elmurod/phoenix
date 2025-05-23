package com.example.phoenix.model.helper;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class OrderStatisticByDayHelper {
  private LocalDate day;
  private Double totalPrice;
  private Integer count;
}
