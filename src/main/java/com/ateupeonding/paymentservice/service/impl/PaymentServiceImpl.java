package com.ateupeonding.paymentservice.service.impl;

import com.ateupeonding.paymentservice.model.PaymentResult;
import com.ateupeonding.paymentservice.model.dto.type.PaymentStatus;
import com.ateupeonding.paymentservice.service.api.PaymentService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public Mono<PaymentResult> processPayment(BigDecimal amount) {
        return Mono.fromSupplier(() -> {
                    PaymentResult result = new PaymentResult();
                    result.setPaymentDate(OffsetDateTime.now());
                    result.setStatus(PaymentStatus.PAID);
                    return result;
                })
                .delayElement(Duration.of(1, ChronoUnit.SECONDS));
    }


}
