package com.ab.saga.paymentservice.application.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserBalanceRequestDto {
    private BigDecimal initialBalance;
}
