package com.ab.saga.orderservice.command.controller;

import com.ab.saga.orderservice.command.dto.OrderRequestDto;
import com.ab.saga.orderservice.command.dto.OrderResponseDto;
import com.ab.saga.orderservice.command.service.CommandOrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class CommandOrderController {

    private final CommandOrderService orderService;

    @PostMapping
    public CompletableFuture<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto);
    }
}
