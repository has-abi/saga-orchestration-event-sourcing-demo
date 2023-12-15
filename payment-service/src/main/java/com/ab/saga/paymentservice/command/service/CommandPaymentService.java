package com.ab.saga.paymentservice.command.service;

import com.ab.commonapi.commands.CreateUserBalanceCommand;
import com.ab.saga.paymentservice.command.dto.UserBalanceRequestDto;
import com.ab.saga.paymentservice.command.dto.UserBalanceResponseDto;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Service
public class CommandPaymentService {

    private final CommandGateway commandGateway;

    public CompletableFuture<UserBalanceResponseDto> createUserBalance(UserBalanceRequestDto requestDto) {
        return commandGateway.send(new CreateUserBalanceCommand(UUID.randomUUID().toString(),
                requestDto.getInitialBalance()));

    }
}

