package com.ab.commonapi.commands;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProcessPaymentCommand extends BaseCommand<String>{

    private final Long userId;
    private final Long orderId;
    private final BigDecimal amount;

    public ProcessPaymentCommand(String id, Long userId, Long orderId, BigDecimal amount) {
        super(id);
        this.userId = userId;
        this.orderId = orderId;
        this.amount = amount;
    }
}
