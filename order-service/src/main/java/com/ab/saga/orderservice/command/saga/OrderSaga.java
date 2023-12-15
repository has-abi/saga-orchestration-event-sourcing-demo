package com.ab.saga.orderservice.command.saga;

import com.ab.commonapi.commands.CancelPaymentCommand;
import com.ab.commonapi.commands.CreateShipmentCommand;
import com.ab.commonapi.commands.ProcessPaymentCommand;
import com.ab.commonapi.commands.UpdateOrderStatusCommand;
import com.ab.commonapi.enums.OrderStatus;
import com.ab.commonapi.enums.PaymentStatus;
import com.ab.commonapi.enums.ShipmentStatus;
import com.ab.commonapi.events.*;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.util.UUID;


@Slf4j
@Saga
public class OrderSaga {

    private static final String ORDER_ID_ASSOCIATION = "orderId";
    private static final String PAYMENT_ID_ASSOCIATION = "userId";
    private static final String SHIPMENT_ID_ASSOCIATION = "shipmentId";

    @Inject
    private CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = ORDER_ID_ASSOCIATION)
    public void on(OrderCreatedEvent orderCreatedEvent) {
        log.info("OrderCreatedEvent: Saga Start");

        SagaLifecycle.associateWith(PAYMENT_ID_ASSOCIATION, orderCreatedEvent.userId());

        commandGateway.send(new ProcessPaymentCommand(orderCreatedEvent.userId(),
                orderCreatedEvent.orderId(),
                orderCreatedEvent.amount()));
    }


    @SagaEventHandler(associationProperty = PAYMENT_ID_ASSOCIATION)
    public void on(PaymentProcessedEvent paymentProcessedEvent) {
        log.info("PaymentProcessedEvent: Saga Continue");

        if (paymentProcessedEvent.paymentStatus().equals(PaymentStatus.PAYMENT_COMPLETED)) {
            String shipmentId = UUID.randomUUID().toString();
            SagaLifecycle.associateWith(SHIPMENT_ID_ASSOCIATION, shipmentId);

            commandGateway.send(new CreateShipmentCommand(shipmentId,
                    paymentProcessedEvent.userId(),
                    paymentProcessedEvent.orderId(),
                    paymentProcessedEvent.amount()));
        }
    }

    @SagaEventHandler(associationProperty = PAYMENT_ID_ASSOCIATION)
    public void on(PaymentFailedEvent paymentFailedEvent) {
        log.info("PaymentFailedEvent: saga continue");

        commandGateway.send(new UpdateOrderStatusCommand(paymentFailedEvent.orderId(),
                OrderStatus.ORDER_FAILED));
    }

    @SagaEventHandler(associationProperty = SHIPMENT_ID_ASSOCIATION)
    public void on(ShipmentCreatedEvent shipmentCreatedEvent) {
        log.info("ShipmentCreatedEvent: saga continue");

        if (shipmentCreatedEvent.shipmentStatus().equals(ShipmentStatus.SHIPMENT_COMPLETED)) {
            commandGateway.send(new UpdateOrderStatusCommand(shipmentCreatedEvent.orderId(),
                    OrderStatus.ORDER_COMPLETED));
        } else {
            commandGateway.send(new CancelPaymentCommand(shipmentCreatedEvent.userId(),
                    shipmentCreatedEvent.orderId(),
                    shipmentCreatedEvent.amount()));
            commandGateway.send(new UpdateOrderStatusCommand(shipmentCreatedEvent.orderId(),
                    OrderStatus.ORDER_FAILED));
        }
    }

    @SagaEventHandler(associationProperty = ORDER_ID_ASSOCIATION)
    public void on(OrderStatusUpdatedEvent orderStatusUpdatedEvent) {
        log.info("OrderStatusUpdatedEvent: end saga: status={}", orderStatusUpdatedEvent.orderStatus());

        SagaLifecycle.end();
    }
}
