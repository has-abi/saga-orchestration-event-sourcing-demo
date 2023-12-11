package com.ab.commonapi.commands;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProcessPaymentCommand extends BaseCommand<Long> {

    private final String orderId;
    private final BigDecimal amount;

    public ProcessPaymentCommand(Long id, String orderId, BigDecimal amount) {
        super(id);
        this.orderId = orderId;
        this.amount = amount;
    }
}
