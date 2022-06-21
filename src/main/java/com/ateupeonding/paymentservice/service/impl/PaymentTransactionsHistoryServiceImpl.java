package com.ateupeonding.paymentservice.service.impl;

import com.ateupeonding.paymentservice.model.entity.PaymentTransactionsHistory;
import com.ateupeonding.paymentservice.model.entity.type.ProductType;
import com.ateupeonding.paymentservice.repository.PaymentTransactionsHistoryRepository;
import com.ateupeonding.paymentservice.service.api.PaymentTransactionsHistoryService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class PaymentTransactionsHistoryServiceImpl implements PaymentTransactionsHistoryService {

    private final PaymentTransactionsHistoryRepository repository;

    public PaymentTransactionsHistoryServiceImpl(PaymentTransactionsHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<PaymentTransactionsHistory> create(PaymentTransactionsHistory item) {
        return repository.save(item);
    }

    @Override
    public Mono<PaymentTransactionsHistory> getById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Flux<PaymentTransactionsHistory> get() {
        return repository.findAll();
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return repository.deleteById(id);
    }

    @Override
    public Flux<Void> deleteByUserId(UUID userId) {
        return repository.deleteAllByUserId(userId);
    }

    @Override
    public Flux<Void> deleteByProductIdAndProductType(UUID productId, ProductType productType) {
        return repository.deleteAllByProductIdAndProductType(productId, productType);
    }

    @Override
    public Flux<PaymentTransactionsHistory> getByUserId(UUID userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public Flux<PaymentTransactionsHistory> getByProductIdAndProductType(UUID productId, ProductType productType) {
        return repository.findAllByProductIdAndProductType(productId, productType);
    }
}
