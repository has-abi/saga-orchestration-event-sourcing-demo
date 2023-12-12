package com.ab.commonapi.commands;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProcessPaymentCommand extends BaseCommand<String> {

    private final String orderId;
    private final BigDecimal amount;

    public ProcessPaymentCommand(String id, String orderId, BigDecimal amount) {
        super(id);
        this.orderId = orderId;
        this.amount = amount;
    }
}
