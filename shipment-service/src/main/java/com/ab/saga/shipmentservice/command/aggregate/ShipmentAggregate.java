package com.ab.saga.shipmentservice.command.aggregate;

import com.ab.commonapi.commands.CreateShipmentCommand;
import com.ab.commonapi.enums.ShipmentStatus;
import com.ab.commonapi.events.ShipmentCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Aggregate
public class ShipmentAggregate {
    @AggregateIdentifier
    private String shipmentId;
    private String userId;
    private String orderId;
    private ShipmentStatus shipmentStatus;

    public ShipmentAggregate() {

    }

    @CommandHandler
    public ShipmentAggregate(CreateShipmentCommand createShipmentCommand) {
        log.info("CreateShipmentCommand received");

        // Simulate shipment failure to fail for any order amount < 20
        boolean failShipment = createShipmentCommand.amount().doubleValue() < 20;
        AggregateLifecycle.apply(new ShipmentCreatedEvent(createShipmentCommand.shipmentId(),
                createShipmentCommand.userId(),
                createShipmentCommand.orderId(),
                createShipmentCommand.amount(),
                failShipment ? ShipmentStatus.SHIPMENT_FAILED : ShipmentStatus.SHIPMENT_COMPLETED
        ));
    }

    @EventSourcingHandler
    public void on(ShipmentCreatedEvent shipmentCreatedEvent) {
        log.info("ShipmentCreatedEvent occurred");

        this.shipmentId = shipmentCreatedEvent.shipmentId();
        this.userId = shipmentCreatedEvent.userId();
        this.orderId = shipmentCreatedEvent.shipmentId();
        this.shipmentStatus = shipmentCreatedEvent.shipmentStatus();
    }

}
