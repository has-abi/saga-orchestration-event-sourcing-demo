package com.ab.saga.paymentservice.application.aggregate;


import com.ab.commonapi.commands.CreateUserBalanceCommand;
import com.ab.commonapi.commands.ProcessPaymentCommand;
import com.ab.commonapi.enums.PaymentStatus;
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
    private Long userId;
    private String orderId;
    private BigDecimal balance;
    private PaymentStatus paymentStatus;

    public PaymentAggregate() {

    }

    @CommandHandler
    public PaymentAggregate(CreateUserBalanceCommand balanceCommand) {
        log.info("ProcessPaymentCommand received");
        if (balanceCommand.getAmount().doubleValue() < 0) {
            throw new RuntimeException("Negative amount");
        }

        AggregateLifecycle.apply(new UserBalanceCreatedEvent(balanceCommand.getId(),
                balanceCommand.getAmount()));
    }

    @EventSourcingHandler
    public void on(UserBalanceCreatedEvent userBalanceCreatedEvent) {
        log.info("UserBalanceCreatedEvent occurred");
        this.balance = userBalanceCreatedEvent.getAmount();
    }

    @CommandHandler
    public void handle(ProcessPaymentCommand paymentCommand) {
        log.info("ProcessPaymentCommand received");
        if (this.balance != null && paymentCommand.getAmount().doubleValue() > this.balance.doubleValue()) {
            throw new RuntimeException("Insufficient balance");
        }

        AggregateLifecycle.apply(new PaymentProcessedEvent(paymentCommand.getId(),
                paymentCommand.getOrderId(),
                paymentCommand.getAmount(),
                PaymentStatus.PAYMENT_COMPLETED));
    }

    @EventSourcingHandler
    public void on(PaymentProcessedEvent paymentProcessedEvent) {
        this.userId = paymentProcessedEvent.getId();
        this.orderId = paymentProcessedEvent.getOrderId();
        this.balance = this.balance.subtract(paymentProcessedEvent.getAmount());
        this.paymentStatus = paymentProcessedEvent.getPaymentStatus();
    }


}
