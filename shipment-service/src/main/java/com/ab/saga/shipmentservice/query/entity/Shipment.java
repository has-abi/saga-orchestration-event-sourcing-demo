package com.ab.saga.shipmentservice.query.entity;

import com.ab.commonapi.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shipment {
    @Id
    private String id;
    private String userId;
    private String orderId;
    @Enumerated(EnumType.STRING)
    private ShipmentStatus shipmentStatus;
}
