package com.ab.saga.paymentservice.infrastructure.messaging;

import lombok.Data;

@Data
public class QueueBinding {
    private String queue;
    private String exchange;
    private String routingKey;
}