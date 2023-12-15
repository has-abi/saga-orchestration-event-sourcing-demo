package com.ab.commonapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

public record CreateShipmentCommand(@TargetAggregateIdentifier String shipmentId, String userId, String orderId, BigDecimal amount) {
}
