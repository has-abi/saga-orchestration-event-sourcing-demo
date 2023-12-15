package com.ab.saga.orderservice.query.dto;

import com.ab.commonapi.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderQueryResponseDto {
    private String id;
    private String userId;
    private BigDecimal amount;
    private OrderStatus orderStatus;
}
