package com.ab.saga.paymentservice.command.controller;

import com.ab.saga.paymentservice.command.dto.UserBalanceRequestDto;
import com.ab.saga.paymentservice.command.dto.UserBalanceResponseDto;
import com.ab.saga.paymentservice.command.service.CommandPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Tag(name = "Payment Balance Command Controller")
@AllArgsConstructor
@RestController
@RequestMapping("/balances")
public class BalanceController {
    private CommandPaymentService commandPaymentService;

    @Operation(summary = "Create new user balance")
    @PostMapping
    public CompletableFuture<UserBalanceResponseDto> createUserBalance(UserBalanceRequestDto requestDto) {
        return commandPaymentService.createUserBalance(requestDto);
    }
}
