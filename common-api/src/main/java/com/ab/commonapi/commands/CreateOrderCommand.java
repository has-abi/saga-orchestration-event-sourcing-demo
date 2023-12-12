package com.ab.commonapi.commands;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateOrderCommand extends BaseCommand<String> {
    private final String userId;
    private final BigDecimal amount;

    public CreateOrderCommand(String id, String userId, BigDecimal amount) {
        super(id);
        this.userId = userId;
        this.amount = amount;
    }
}
