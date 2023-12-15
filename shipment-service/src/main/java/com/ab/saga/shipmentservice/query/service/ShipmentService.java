package com.ab.saga.shipmentservice.query.service;

import com.ab.commonapi.events.ShipmentCreatedEvent;
import com.ab.saga.shipmentservice.query.entity.Shipment;
import com.ab.saga.shipmentservice.query.repository.ShipmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;

    @EventHandler
    public void handle(ShipmentCreatedEvent shipmentCreatedEvent) {
        Shipment shipment = new Shipment();

        shipment.setId(shipmentCreatedEvent.shipmentId());
        shipment.setUserId(shipmentCreatedEvent.userId());
        shipment.setOrderId(shipmentCreatedEvent.orderId());
        shipment.setShipmentStatus(shipmentCreatedEvent.shipmentStatus());

        shipmentRepository.save(shipment);
    }
}
