package com.ab.saga.paymentservice.command.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserBalanceRequestDto {
    private BigDecimal initialBalance;
}
