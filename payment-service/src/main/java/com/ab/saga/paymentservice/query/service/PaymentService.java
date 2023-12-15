package com.ab.saga.paymentservice.query.service;

import com.ab.commonapi.events.PaymentCancelledEvent;
import com.ab.commonapi.events.PaymentProcessedEvent;
import com.ab.commonapi.events.UserBalanceCreatedEvent;
import com.ab.saga.paymentservice.query.entity.Balance;
import com.ab.saga.paymentservice.query.entity.Transaction;
import com.ab.saga.paymentservice.query.repository.BalanceRepository;
import com.ab.saga.paymentservice.query.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final BalanceRepository balanceRepository;
    private final TransactionRepository transactionRepository;

    @EventHandler
    public void handleUserBalanceCreated(UserBalanceCreatedEvent userBalanceCreatedEvent) {
        Balance balance = new Balance();
        balance.setUserId(userBalanceCreatedEvent.userId());
        balance.setBalance(userBalanceCreatedEvent.amount());

        balanceRepository.save(balance);
    }

    @Transactional
    @EventHandler
    public void on(PaymentProcessedEvent paymentProcessedEvent) {
        var userBalance = balanceRepository.findByUserId(paymentProcessedEvent.userId());
        userBalance.ifPresent(balance -> {
            balance.setBalance(balance.getBalance().subtract(paymentProcessedEvent.amount()));

            Transaction transaction = new Transaction();
            transaction.setUserId(paymentProcessedEvent.userId());
            transaction.setOrderId(paymentProcessedEvent.orderId());
            transaction.setAmount(paymentProcessedEvent.amount());

            transactionRepository.save(transaction);
        });
    }

    @Transactional
    @EventHandler
    public void on(PaymentCancelledEvent paymentCancelledEvent) {
        transactionRepository.findByOrderId(paymentCancelledEvent.orderId())
                .ifPresent(transaction -> balanceRepository.findByUserId(paymentCancelledEvent.userId())
                        .ifPresent(balance -> {
                            balance.setBalance(balance.getBalance().add(transaction.getAmount()));
                            transactionRepository.delete(transaction);
                        })
                );
    }
}
