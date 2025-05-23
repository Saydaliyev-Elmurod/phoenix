package com.example.phoenix.model.mapper;

import com.example.phoenix.model.helper.TotalOrderStatisticHelper;
import com.example.phoenix.model.response.OrderStatisticByDay;
import com.example.phoenix.model.response.TotalOrderStatistic;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = InstantMapper.class)
public abstract class OrderStatisticMapper {

    public static final OrderStatisticMapper INSTANCE = Mappers.getMapper(OrderStatisticMapper.class);

    public abstract TotalOrderStatistic toTotal(TotalOrderStatisticHelper helper);

    public abstract OrderStatisticByDay toDay
            (OrderStatisticByDay helper);

}
