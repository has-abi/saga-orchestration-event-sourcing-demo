package com.ab.saga.orderservice.application.aggregate;

import com.ab.commonapi.commands.CreateOrderCommand;
import com.ab.commonapi.enums.OrderStatus;
import com.ab.commonapi.events.OrderCreatedEvent;
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
    private String aggregateId;
    private Long orderId;
    private Long userId;
    private BigDecimal amount;
    private OrderStatus orderStatus;

    public OrderAggregate() {

    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        log.info("CreateOrderCommand received");
        AggregateLifecycle.apply(new OrderCreatedEvent(createOrderCommand.getId(),
                createOrderCommand.getUserId(),
                createOrderCommand.getAmount(),
                OrderStatus.ORDER_CREATED));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        log.info("OrderCreatedEvent occurred");

        this.aggregateId = orderCreatedEvent.getId();
        this.userId = orderCreatedEvent.getUserId();
        this.amount = orderCreatedEvent.getAmount();
        this.orderStatus = orderCreatedEvent.getOrderStatus();
    }

}
