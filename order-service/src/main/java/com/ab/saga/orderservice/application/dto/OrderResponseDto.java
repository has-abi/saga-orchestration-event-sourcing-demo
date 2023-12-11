package com.ab.saga.orderservice.application.dto;

import com.ab.commonapi.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Long orderId;
    private Long userId;
    private OrderStatus orderStatus;
}
