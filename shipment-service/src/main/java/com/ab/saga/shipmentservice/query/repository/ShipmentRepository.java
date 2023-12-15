package com.ab.saga.shipmentservice.query.repository;

import com.ab.saga.shipmentservice.query.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
