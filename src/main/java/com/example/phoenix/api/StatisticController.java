package com.example.phoenix.api;

import com.example.phoenix.model.response.OrderStatisticByDay;

import java.time.Instant;
import java.util.List;

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

  @GetMapping("/day")
  public List<OrderStatisticByDay> reportByDay(
      @RequestParam(required = false) Boolean isPaid,
      @RequestParam(required = false) Instant dateFrom,
      @RequestParam(required = false) Instant dateTo) {
    return statisticService.reportByDay(dateFrom, dateTo, isPaid);
  }
  @GetMapping("/total")
  public List<TotalOrderStatistic> reportTotalOrder(
          @RequestParam(required = false) Instant dateFrom,
          @RequestParam(required = false) Instant dateTo) {
    return statisticService.reportTotalOrder(dateFrom, dateTo);
  }
}
