package com.ab.commonapi.events;

import java.math.BigDecimal;

public record UserBalanceCreatedEvent(String userId, BigDecimal amount) {
}
