package com.ab.saga.paymentservice.command.controller;

import com.ab.saga.paymentservice.command.dto.UserBalanceRequestDto;
import com.ab.saga.paymentservice.command.dto.UserBalanceResponseDto;
import com.ab.saga.paymentservice.command.service.CommandPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@RestController
@RequestMapping("/balances")
public class BalanceController {
    private CommandPaymentService commandPaymentService;

    @PostMapping
    public CompletableFuture<UserBalanceResponseDto> createUserBalance(UserBalanceRequestDto requestDto) {
        return commandPaymentService.createUserBalance(requestDto);
    }
}
