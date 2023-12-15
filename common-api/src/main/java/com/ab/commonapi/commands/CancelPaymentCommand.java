package com.ab.commonapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

public record CancelPaymentCommand(@TargetAggregateIdentifier String userId, String orderId, BigDecimal amount) {
}
