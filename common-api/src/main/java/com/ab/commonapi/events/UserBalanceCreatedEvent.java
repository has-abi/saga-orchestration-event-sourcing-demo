package com.ab.commonapi.events;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UserBalanceCreatedEvent extends BaseEvent<String> {
    private final BigDecimal amount;

    public UserBalanceCreatedEvent(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}
