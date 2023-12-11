package com.ab.saga.paymentservice.application.service;

import com.ab.saga.paymentservice.application.dto.UserBalanceRequestDto;
import com.ab.saga.paymentservice.application.dto.UserBalanceResponseDto;

import java.util.concurrent.CompletableFuture;

public interface PaymentService {
    CompletableFuture<UserBalanceResponseDto> createUserBalance(UserBalanceRequestDto requestDto);
}
