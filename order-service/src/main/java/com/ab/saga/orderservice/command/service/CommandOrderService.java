package com.ab.saga.orderservice.command.service;

import com.ab.commonapi.commands.CreateOrderCommand;
import com.ab.saga.orderservice.command.dto.OrderRequestDto;
import com.ab.saga.orderservice.command.dto.OrderResponseDto;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Service
public class CommandOrderService {
    private final CommandGateway commandGateway;

    public CompletableFuture<OrderResponseDto> createOrder(OrderRequestDto orderRequestDto) {
        return commandGateway.send(new CreateOrderCommand(UUID.randomUUID().toString(),
                orderRequestDto.getUserId(), orderRequestDto.getAmount()));
    }
}
