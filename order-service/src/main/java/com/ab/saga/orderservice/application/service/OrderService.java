package com.ab.saga.orderservice.application.service;

import com.ab.saga.orderservice.application.dto.OrderRequestDto;
import com.ab.saga.orderservice.application.dto.OrderResponseDto;

import java.util.concurrent.CompletableFuture;

public interface OrderService {
    CompletableFuture<OrderResponseDto> createOrder(OrderRequestDto orderRequestDto);

//    void cancelOrder(PaymentProcessedEvent eventDto);
//
//    void confirmOrder(ShipmentProcessedEvent eventDto);
}
