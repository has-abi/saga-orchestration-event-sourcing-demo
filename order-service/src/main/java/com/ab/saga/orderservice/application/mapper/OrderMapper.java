package com.ab.saga.orderservice.application.mapper;

import com.ab.commonapi.events.OrderCreatedEvent;
import com.ab.saga.orderservice.application.dto.OrderRequestDto;
import com.ab.saga.orderservice.application.dto.OrderResponseDto;
import com.ab.saga.orderservice.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    Order orderRequestDtoToOrder(OrderRequestDto orderRequestDto);

    @Mapping(target = "orderId", source = "id")
    OrderResponseDto orderToOrderResponseDto(Order order);

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "instant", expression = "java( new java.util.Date() )")
    OrderCreatedEvent orderToOrderCreatedEventDto(Order order);
}
