package com.ab.commonapi.events;

import com.ab.commonapi.enums.OrderStatus;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class OrderCreatedEvent extends BaseEvent<String> {
    private final String userId;
    private final BigDecimal amount;
    private final OrderStatus orderStatus;

    public OrderCreatedEvent(String id, String userId, BigDecimal amount, OrderStatus orderStatus) {
        super(id);
        this.userId = userId;
        this.amount = amount;
        this.orderStatus = orderStatus;
    }
}