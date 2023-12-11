package com.ab.commonapi.commands;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateOrderCommand extends BaseCommand<String> {
    private final Long userId;
    private final BigDecimal amount;

    public CreateOrderCommand(String id, Long userId, BigDecimal amount) {
        super(id);
        this.userId = userId;
        this.amount = amount;
    }
}
