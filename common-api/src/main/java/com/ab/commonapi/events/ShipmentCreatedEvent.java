package com.ab.commonapi.events;

import com.ab.commonapi.enums.ShipmentStatus;

import java.math.BigDecimal;

public record ShipmentCreatedEvent(String shipmentId, String userId, String orderId, BigDecimal amount, ShipmentStatus shipmentStatus) {
}
