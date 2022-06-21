package com.ateupeonding.paymentservice.service.api;

import com.ateupeonding.paymentservice.model.PaymentResult;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface PaymentService {

    Mono<PaymentResult> processPayment(BigDecimal amount);

}
