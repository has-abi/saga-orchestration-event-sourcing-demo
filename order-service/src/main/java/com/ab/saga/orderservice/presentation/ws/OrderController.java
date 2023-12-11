package com.ab.saga.orderservice.presentation.ws;

import com.ab.saga.orderservice.application.dto.OrderRequestDto;
import com.ab.saga.orderservice.application.dto.OrderResponseDto;

import java.util.concurrent.CompletableFuture;

public interface OrderController {
    CompletableFuture<OrderResponseDto> createOrder(OrderRequestDto orderRequestDto);
}
