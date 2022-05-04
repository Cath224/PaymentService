package com.ateupeonding.paymentservice.controller;

import com.ateupeonding.paymentservice.mapper.PaymentTransactionsHistoryMapper;
import com.ateupeonding.paymentservice.model.dto.PaymentTransactionsHistoryDto;
import com.ateupeonding.paymentservice.model.entity.PaymentTransactionsHistory;
import com.ateupeonding.paymentservice.service.api.PaymentTransactionsHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(ApiPath.HISTORY.PATH)
public class PaymentTransactionsHistoryController {

    @Autowired
    private PaymentTransactionsHistoryService service;

    @Autowired
    private PaymentTransactionsHistoryMapper mapper;


    @GetMapping
    public Flux<PaymentTransactionsHistoryDto> get(@RequestParam(required = false) UUID userId) {
        Flux<PaymentTransactionsHistory> result;
        if (userId == null) {
            result = service.get();
        } else {
            result = service.getByUserId(userId);
        }
        return result
                .map(mapper::toDto);
    }

    @GetMapping("{id}")
    public Mono<PaymentTransactionsHistoryDto> getById(@PathVariable UUID id) {
        return service.getById(id)
                .map(mapper::toDto);
    }

}
