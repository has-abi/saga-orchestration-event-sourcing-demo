package com.ab.commonapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

public record CreateUserBalanceCommand(@TargetAggregateIdentifier String userId, BigDecimal amount) {
}
