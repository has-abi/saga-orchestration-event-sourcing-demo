package com.ab.saga.orderservice.command.controller;

import com.ab.saga.orderservice.command.dto.OrderRequestDto;
import com.ab.saga.orderservice.command.dto.OrderResponseDto;
import com.ab.saga.orderservice.command.service.CommandOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Tag(name = "Command Order Controller", description = "Order command requests controller")
@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class CommandOrderController {

    private final CommandOrderService orderService;

    @Operation(summary = "Create new order")
    @PostMapping
    public CompletableFuture<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto);
    }
}
