package com.ab.saga.orderservice.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetOrderRequestDto {
    private String orderId;
}
