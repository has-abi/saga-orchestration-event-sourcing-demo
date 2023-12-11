package com.ab.commonapi.commands;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateUserBalanceCommand extends BaseCommand<String> {
    private final BigDecimal amount;

    public CreateUserBalanceCommand(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}
