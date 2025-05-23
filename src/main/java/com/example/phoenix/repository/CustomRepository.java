package com.example.phoenix.repository;

import com.example.phoenix.model.helper.OrderStatisticByDayHelper;
import com.example.phoenix.model.helper.TotalOrderStatisticHelper;
import com.example.phoenix.util.Constants;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
@RequiredArgsConstructor
public class CustomRepository {
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public List<OrderStatisticByDayHelper> reportByDay(
      Instant dateFrom, Instant dateTo, Boolean isPaid) {
    Map<String, Object> parameters = new HashMap<>();
    String query =
        " select DATE(created_date) as day, sum(total_price) as totalPrice, count(*) as count"
            + " from "
            + Constants.SCHEMA
            + "."
            + Constants.TABLE_ORDER
            + " where deleted = false ";

    if (isPaid != null) {
      query += "and is_paid = :isPaid ";
      parameters.put("isPaid", isPaid);
    }
    if (dateFrom != null) {
      query += "and created_date >= :dateFrom ";
      parameters.put("dateFrom", Timestamp.from(dateFrom));
    }
    if (dateTo != null) {
      query += "and created_date <= :dateTo ";
      parameters.put("dateTo", Timestamp.from(dateTo));
    }

    query += " group by DATE(created_date)";

    return namedParameterJdbcTemplate.query(
        query, parameters, new BeanPropertyRowMapper<>(OrderStatisticByDayHelper.class));
  }

  public List<TotalOrderStatisticHelper> reportTotalOrder(Instant dateFrom, Instant dateTo) {
    Map<String, Object> parameters = new HashMap<>();
    String query = "select " +
            "sum(case when is_paid = true then 1 else 0 end) as totalPaidCount, " +
            "sum(case when is_paid = true then total_price else 0 end) as totalPaidPrice, " +
            "sum(case when is_paid = false then 1 else 0 end) as totalNotPaidCount, " +
            "sum(case when is_paid = false then total_price else 0 end) as totalNotPaidPrice " +
            "from " + Constants.SCHEMA + "." + Constants.TABLE_ORDER +
            " where deleted = false ";

    if (dateFrom != null) {
      query += "and created_date >= :dateFrom ";
      parameters.put("dateFrom", Timestamp.from(dateFrom));
    }
    if (dateTo != null) {
      query += "and created_date <= :dateTo ";
      parameters.put("dateTo", Timestamp.from(dateTo));
    }

    return namedParameterJdbcTemplate.query(
            query, parameters, new BeanPropertyRowMapper<>(TotalOrderStatisticHelper.class));
  }
}
