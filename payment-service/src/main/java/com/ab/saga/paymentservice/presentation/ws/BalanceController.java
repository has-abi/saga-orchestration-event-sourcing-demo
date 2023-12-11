package com.ab.saga.paymentservice.presentation.ws;

import com.ab.saga.paymentservice.application.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/balances")
public class BalanceController {
    private PaymentService paymentService;


}
