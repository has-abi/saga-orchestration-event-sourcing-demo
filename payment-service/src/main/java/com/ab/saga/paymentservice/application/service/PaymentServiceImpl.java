package com.ab.saga.paymentservice.application.service;


import com.ab.commonapi.commands.CreateUserBalanceCommand;
import com.ab.commonapi.events.PaymentProcessedEvent;
import com.ab.commonapi.events.UserBalanceCreatedEvent;
import com.ab.saga.paymentservice.application.dto.UserBalanceRequestDto;
import com.ab.saga.paymentservice.application.dto.UserBalanceResponseDto;
import com.ab.saga.paymentservice.domain.entity.Balance;
import com.ab.saga.paymentservice.domain.repository.BalanceRepository;
import com.ab.saga.paymentservice.domain.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Slf4j
@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final BalanceRepository balanceRepository;
    private final TransactionRepository transactionRepository;
    private final CommandGateway commandGateway;

    @Override
    public CompletableFuture<UserBalanceResponseDto> createUserBalance(UserBalanceRequestDto requestDto) {
        return commandGateway.send(new CreateUserBalanceCommand(UUID.randomUUID().toString(),
                requestDto.getInitialBalance()));
    }

    @EventHandler
    public void on(PaymentProcessedEvent paymentProcessedEvent) {
        var userBalance = balanceRepository.findByUserId(paymentProcessedEvent.getId());
        userBalance.ifPresent(balance -> {
            balance.setBalance(balance.getBalance());
        });
    }

    @EventHandler
    public void handleUserBalanceCreated(UserBalanceCreatedEvent userBalanceCreatedEvent) {
        Balance balance = new Balance();
        balance.setUserId(userBalanceCreatedEvent.getId());
        balance.setBalance(userBalanceCreatedEvent.getAmount());

        balanceRepository.save(balance);
    }
}
