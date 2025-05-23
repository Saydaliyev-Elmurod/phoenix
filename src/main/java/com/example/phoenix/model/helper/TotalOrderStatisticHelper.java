package com.example.phoenix.model.helper;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalOrderStatisticHelper {
    private Integer totalPaidCount;
    private Integer totalPaidPrice;
    private Integer totalNotPaidCount;
    private Integer totalNotPaidPrice;
}
