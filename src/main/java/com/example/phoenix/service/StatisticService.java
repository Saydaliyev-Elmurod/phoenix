package com.example.phoenix.service;

import com.example.phoenix.model.mapper.OrderStatisticMapper;
import com.example.phoenix.model.response.OrderStatisticByDay;
import com.example.phoenix.model.response.ProductStatistic;
import com.example.phoenix.model.response.TotalOrderStatistic;
import com.example.phoenix.repository.CustomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class StatisticService {
    private final CustomRepository customRepository;

    public List<OrderStatisticByDay> getOrderStatisticsByDay(Instant startDate, Instant endDate, Boolean isPaid) {
        log.debug("Getting daily order statistics for period: {} to {}, isPaid: {}", startDate, endDate, isPaid);
        return customRepository.reportByDay(startDate, endDate, isPaid).stream()
                .map(
                        orderStats ->
                                new OrderStatisticByDay(orderStats.getDay(), orderStats.getTotalPrice(), orderStats.getCount()))
                .toList();
    }

    public List<TotalOrderStatistic> getTotalOrderStatistics(Instant startDate, Instant endDate) {
        log.debug("Getting total order statistics for period: {} to {}", startDate, endDate);
        return customRepository.reportTotalOrder(startDate, endDate).stream()
                .map(
                        OrderStatisticMapper.INSTANCE::toTotal)
                .toList();
    }

    public List<ProductStatistic> getProductOrderStatistics(Instant startDate, Instant endDate) {
        log.debug("Getting product order statistics for period: {} to {}", startDate, endDate);
        return customRepository.getProductStatistic(startDate, endDate).stream()
                .map(
                        OrderStatisticMapper.INSTANCE::toProduct)
                .toList();
    }
}
