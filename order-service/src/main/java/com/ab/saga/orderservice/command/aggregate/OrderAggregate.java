package com.ab.saga.orderservice.command.aggregate;

import com.ab.commonapi.commands.CreateOrderCommand;
import com.ab.commonapi.commands.UpdateOrderStatusCommand;
import com.ab.commonapi.enums.OrderStatus;
import com.ab.commonapi.events.OrderCreatedEvent;
import com.ab.commonapi.events.OrderStatusUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Slf4j
@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private String orderId;
    private String userId;
    private BigDecimal amount;
    private OrderStatus orderStatus;

    public OrderAggregate() {

    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        log.info("CreateOrderCommand received");
        AggregateLifecycle.apply(new OrderCreatedEvent(createOrderCommand.orderId(),
                createOrderCommand.userId(),
                createOrderCommand.amount(),
                OrderStatus.ORDER_CREATED));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        log.info("OrderCreatedEvent occurred");

        this.orderId = orderCreatedEvent.orderId();
        this.userId = orderCreatedEvent.userId();
        this.amount = orderCreatedEvent.amount();
        this.orderStatus = orderCreatedEvent.orderStatus();
    }

    @CommandHandler
    public void handle(UpdateOrderStatusCommand updateOrderStatusCommand) {
        log.info("UpdateOrderStatusCommand received");
        AggregateLifecycle.apply(new OrderStatusUpdatedEvent(updateOrderStatusCommand.orderId(),
                updateOrderStatusCommand.orderStatus()));
    }

    @EventSourcingHandler
    public void on(OrderStatusUpdatedEvent orderStatusUpdatedEvent) {
        log.info("OrderStatusUpdatedEvent occurred");

        this.orderId = orderStatusUpdatedEvent.orderId();
        this.orderStatus = orderStatusUpdatedEvent.orderStatus();
    }


}
