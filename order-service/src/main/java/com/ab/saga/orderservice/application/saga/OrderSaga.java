package com.ab.saga.orderservice.application.saga;

import com.ab.commonapi.commands.ProcessPaymentCommand;
import com.ab.commonapi.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

@Slf4j
@Saga
public class OrderSaga {

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void on(OrderCreatedEvent orderCreatedEvent, CommandGateway commandGateway) {
        log.info("OrderCreatedEvent: Saga Start");


        SagaLifecycle.associateWith("id", orderCreatedEvent.getUserId());

        commandGateway.send(new ProcessPaymentCommand(orderCreatedEvent.getUserId(),
                orderCreatedEvent.getId(),
                orderCreatedEvent.getAmount()));
    }


}
