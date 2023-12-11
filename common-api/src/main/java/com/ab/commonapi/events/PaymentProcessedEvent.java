package com.ab.commonapi.events;

import com.ab.commonapi.enums.PaymentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PaymentProcessedEvent {
    private Long userId;
    private Long orderId;
    private PaymentStatus paymentStatus;
    private Date instant = new Date();

    public PaymentProcessedEvent(Long userId, Long orderId) {
        this.userId = userId;
        this.orderId = orderId;
    }
}
