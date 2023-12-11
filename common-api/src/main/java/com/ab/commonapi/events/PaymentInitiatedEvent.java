package com.ab.commonapi.events;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PaymentInitiatedEvent extends BaseEvent<String> {
    private final Long userId;
    private final String orderId;
    private final BigDecimal amount;

    public PaymentInitiatedEvent(String id, Long userId, String orderId, BigDecimal amount) {
        super(id);
        this.userId = userId;
        this.orderId = orderId;
        this.amount = amount;
    }
}
