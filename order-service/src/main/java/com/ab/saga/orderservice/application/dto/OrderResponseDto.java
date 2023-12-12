package com.ab.saga.orderservice.application.dto;

import com.ab.commonapi.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Long orderId;
    private String userId;
    private OrderStatus orderStatus;
}
