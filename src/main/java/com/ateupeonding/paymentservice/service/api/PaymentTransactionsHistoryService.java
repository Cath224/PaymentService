package com.ateupeonding.paymentservice.service.api;

import com.ateupeonding.paymentservice.model.entity.PaymentTransactionsHistory;
import com.ateupeonding.paymentservice.model.entity.type.ProductType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PaymentTransactionsHistoryService {

    Mono<PaymentTransactionsHistory> create(PaymentTransactionsHistory item);

    Mono<PaymentTransactionsHistory> getById(UUID id);

    Flux<PaymentTransactionsHistory> get();

    Mono<Void> deleteById(UUID id);

    Flux<Void> deleteByUserId(UUID userId);

    Flux<Void> deleteByProductIdAndProductType(UUID productId, ProductType productType);

    Flux<PaymentTransactionsHistory> getByUserId(UUID userId);

    Flux<PaymentTransactionsHistory> getByProductIdAndProductType(UUID productId, ProductType productType);

}
