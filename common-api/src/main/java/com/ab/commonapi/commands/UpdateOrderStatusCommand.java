package com.ab.commonapi.commands;

import com.ab.commonapi.enums.OrderStatus;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record UpdateOrderStatusCommand(@TargetAggregateIdentifier String orderId, OrderStatus orderStatus) {
}
