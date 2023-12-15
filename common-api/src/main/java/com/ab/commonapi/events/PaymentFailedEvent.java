package com.ab.commonapi.events;

import com.ab.commonapi.enums.PaymentStatus;

public record PaymentFailedEvent(String userId, String orderId, PaymentStatus paymentStatus) {
}
