package com.ab.commonapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

public record CreateOrderCommand(@TargetAggregateIdentifier String orderId, String userId, BigDecimal amount) {
}
