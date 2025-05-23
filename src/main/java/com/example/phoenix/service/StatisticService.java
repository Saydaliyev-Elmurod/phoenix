package com.example.phoenix.service;

import com.example.phoenix.model.mapper.OrderStatisticMapper;
import com.example.phoenix.model.response.OrderStatisticByDay;
import com.example.phoenix.model.response.TotalOrderStatistic;
import com.example.phoenix.repository.CustomRepository;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class StatisticService {
    private final CustomRepository customRepository;

    public List<OrderStatisticByDay> reportByDay(Instant dateFrom, Instant dateTo, Boolean isPaid) {
        log.debug("Report by day with dateFrom: {}, dateTo: {}, isPaid: {}", dateFrom, dateTo, isPaid);
        return customRepository.reportByDay(dateFrom, dateTo, isPaid).stream()
                .map(
                        report ->
                                new OrderStatisticByDay(report.getDay(), report.getTotalPrice(), report.getCount()))
                .toList();
    }

    public List<TotalOrderStatistic> reportTotalOrder(Instant dateFrom, Instant dateTo) {

        log.debug("Report total order with dateFrom: {}, dateTo: {}", dateFrom, dateTo);
        return customRepository.reportTotalOrder(dateFrom, dateTo).stream()
                .map(
                        OrderStatisticMapper.INSTANCE::toTotal)
                .toList();
    }
}
