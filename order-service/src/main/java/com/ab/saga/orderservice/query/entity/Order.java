package com.ab.saga.orderservice.query.entity;

import com.ab.commonapi.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    private String id;
    private String userId;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
