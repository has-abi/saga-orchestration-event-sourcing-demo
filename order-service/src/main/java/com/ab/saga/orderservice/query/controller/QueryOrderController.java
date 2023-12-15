package com.ab.saga.orderservice.query.controller;

import com.ab.saga.orderservice.query.dto.GetAllOrdersQueryDto;
import com.ab.saga.orderservice.query.dto.GetOrderRequestDto;
import com.ab.saga.orderservice.query.dto.OrderQueryResponseDto;
import com.ab.saga.orderservice.query.service.EventSourcingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@Tag(name = "Query Order Controller", description = "Order query requests controller")
@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class QueryOrderController {

    private final QueryGateway queryGateway;
    private EventSourcingService eventSourcingService;

    @Operation(summary = "Get all orders")
    @GetMapping
    public List<OrderQueryResponseDto> getAllOrders() {
        return queryGateway.query(new GetAllOrdersQueryDto(),
                ResponseTypes.multipleInstancesOf(OrderQueryResponseDto.class)).join();
    }

    @Operation(summary = "Get Order by id")
    @GetMapping("/{orderId}")
    public OrderQueryResponseDto findById(@PathVariable String orderId) {
        return queryGateway.query(new GetOrderRequestDto(orderId), OrderQueryResponseDto.class).join();
    }

    @Operation(summary = "Get order events by id")
    @GetMapping("/{orderId}/events")
    public Stream<?> getOrderEvents(@PathVariable String orderId) {
        return eventSourcingService.getOrderEvents(orderId);
    }

}
