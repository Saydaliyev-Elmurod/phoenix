package com.example.phoenix.api;

import com.example.phoenix.model.response.OrderStatisticByDay;

import java.time.Instant;
import java.util.List;

import com.example.phoenix.model.response.ProductStatistic;
import com.example.phoenix.model.response.TotalOrderStatistic;
import com.example.phoenix.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("api/v1/statistic")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping("/daily")
    public List<OrderStatisticByDay> getDailyOrderStatistics(
            @RequestParam(required = false) Boolean isPaid,
            @RequestParam(required = false) Instant startDate,
            @RequestParam(required = false) Instant endDate) {
        return statisticService.getOrderStatisticsByDay(startDate, endDate, isPaid);
    }

    @GetMapping("/total-orders")
    public List<TotalOrderStatistic> getTotalOrderStatistics(
            @RequestParam(required = false) Instant startDate,
            @RequestParam(required = false) Instant endDate) {
        return statisticService.getTotalOrderStatistics(startDate, endDate);
    }

    @GetMapping("/products")
    public List<ProductStatistic> getProductOrderStatistics(
            @RequestParam(required = false) Instant startDate,
            @RequestParam(required = false) Instant endDate) {
        return statisticService.getProductOrderStatistics(startDate, endDate);
    }
}
