package com.ab.commonapi.events;

import com.ab.commonapi.enums.PaymentStatus;

import java.math.BigDecimal;

public record PaymentCancelledEvent(String userId, String orderId, BigDecimal amount, PaymentStatus paymentStatus) {
}
