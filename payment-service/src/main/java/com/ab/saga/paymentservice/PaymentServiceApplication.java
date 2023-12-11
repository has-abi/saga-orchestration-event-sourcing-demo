package com.ab.saga.paymentservice;

import com.ab.saga.paymentservice.domain.entity.Balance;
import com.ab.saga.paymentservice.domain.repository.BalanceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
public class PaymentServiceApplication {
    private final BalanceRepository balanceRepository;

    public PaymentServiceApplication(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Map<Long, Double> balanceData = Map.of(
                    1L, 200.0,
                    2L, 300.0,
                    3L, 1000.0,
                    4L, 2500.0,
                    999L, 2500.0
            );
            balanceData.forEach((userId, balance) -> {
                if(balanceRepository.findByUserId(userId).isEmpty()) {
                    Balance userBalance = new Balance();
                    userBalance.setUserId(userId);
                    userBalance.setBalance(balance);
                    balanceRepository.save(userBalance);
                }
            });
        };
    }
}
