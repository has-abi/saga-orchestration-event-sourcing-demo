package com.ab.saga.paymentservice.command.aggregate;


import com.ab.commonapi.commands.CancelPaymentCommand;
import com.ab.commonapi.commands.CreateUserBalanceCommand;
import com.ab.commonapi.commands.ProcessPaymentCommand;
import com.ab.commonapi.enums.PaymentStatus;
import com.ab.commonapi.events.PaymentCancelledEvent;
import com.ab.commonapi.events.PaymentFailedEvent;
import com.ab.commonapi.events.PaymentProcessedEvent;
import com.ab.commonapi.events.UserBalanceCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Slf4j
@Aggregate
public class PaymentAggregate {
    @AggregateIdentifier
    private String userId;
    private String orderId;
    private BigDecimal balance;
    private PaymentStatus paymentStatus;

    public PaymentAggregate() {

    }

    @CommandHandler
    public PaymentAggregate(CreateUserBalanceCommand balanceCommand) {
        log.info("CreateUserBalanceCommand received");

        if (balanceCommand.amount().doubleValue() < 0) {
            throw new RuntimeException("Negative amount");
        }

        AggregateLifecycle.apply(new UserBalanceCreatedEvent(balanceCommand.userId(),
                balanceCommand.amount()));
    }

    @EventSourcingHandler
    public void on(UserBalanceCreatedEvent userBalanceCreatedEvent) {
        log.info("UserBalanceCreatedEvent occurred");

        this.userId = userBalanceCreatedEvent.userId();
        this.balance = userBalanceCreatedEvent.amount();
    }

    @CommandHandler
    public void handle(ProcessPaymentCommand paymentCommand) {
        log.info("ProcessPaymentCommand received");

        if (this.balance != null && paymentCommand.amount().doubleValue() > this.balance.doubleValue()) {
            AggregateLifecycle.apply(new PaymentFailedEvent(paymentCommand.userId(),
                    paymentCommand.orderId(),
                    PaymentStatus.PAYMENT_FAILED));
        } else {
            AggregateLifecycle.apply(new PaymentProcessedEvent(paymentCommand.userId(),
                    paymentCommand.orderId(),
                    paymentCommand.amount(),
                    PaymentStatus.PAYMENT_COMPLETED));
        }
    }

    @EventSourcingHandler
    public void on(PaymentProcessedEvent paymentProcessedEvent) {
        log.info("PaymentProcessedEvent occurred");

        this.userId = paymentProcessedEvent.userId();
        this.orderId = paymentProcessedEvent.orderId();
        this.balance = this.balance.subtract(paymentProcessedEvent.amount());
        this.paymentStatus = paymentProcessedEvent.paymentStatus();
    }

    @CommandHandler
    public void handle(CancelPaymentCommand cancelPaymentCommand) {
        log.info("CancelPaymentCommand received");

        AggregateLifecycle.apply(new PaymentCancelledEvent(cancelPaymentCommand.userId(),
                cancelPaymentCommand.orderId(),
                cancelPaymentCommand.amount(),
                PaymentStatus.PAYMENT_CANCELLED));
    }


    @EventSourcingHandler
    public void on(PaymentCancelledEvent paymentCancelledEvent) {
        log.info("PaymentCancelledEvent occurred");

        this.userId = paymentCancelledEvent.userId();
        this.orderId = paymentCancelledEvent.orderId();
        this.paymentStatus = paymentCancelledEvent.paymentStatus();
        this.balance = this.balance.add(paymentCancelledEvent.amount());
    }
}
