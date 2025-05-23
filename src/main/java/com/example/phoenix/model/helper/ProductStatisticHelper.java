package com.example.phoenix.model.helper;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductStatisticHelper {
    private UUID id;
    private String name;
    private Integer count;
    private Double totalPrice;
}
