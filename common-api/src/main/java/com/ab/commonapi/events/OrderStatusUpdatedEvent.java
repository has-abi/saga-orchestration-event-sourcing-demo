package com.ab.commonapi.events;

import com.ab.commonapi.enums.OrderStatus;

public record OrderStatusUpdatedEvent(String orderId, OrderStatus orderStatus) {
}
