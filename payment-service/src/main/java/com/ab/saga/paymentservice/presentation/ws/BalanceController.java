package com.ab.saga.paymentservice.presentation.ws;

import com.ab.saga.paymentservice.application.dto.UserBalanceRequestDto;
import com.ab.saga.paymentservice.application.dto.UserBalanceResponseDto;
import com.ab.saga.paymentservice.application.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@RestController
@RequestMapping("/balances")
public class BalanceController {
    private PaymentService paymentService;

    @PostMapping
    public CompletableFuture<UserBalanceResponseDto> createUserBalance(UserBalanceRequestDto requestDto) {
        return paymentService.createUserBalance(requestDto);
    }
}
