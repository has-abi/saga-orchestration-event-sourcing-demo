package com.ab.commonapi.events;

import com.ab.commonapi.enums.PaymentStatus;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PaymentProcessedEvent extends BaseEvent<String> {
    private final String orderId;
    private final BigDecimal amount;
    private final PaymentStatus paymentStatus;

    public PaymentProcessedEvent(String id, String orderId, BigDecimal amount, PaymentStatus paymentStatus) {
        super(id);
        this.orderId = orderId;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }
}
