package com.ab.commonapi.events;

import com.ab.commonapi.enums.OrderStatus;

import java.math.BigDecimal;

public record OrderCreatedEvent(String orderId, String userId, BigDecimal amount, OrderStatus orderStatus) {
}