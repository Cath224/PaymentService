package com.ateupeonding.paymentservice.repository;

import com.ateupeonding.paymentservice.model.entity.PaymentTransactionsHistory;
import com.ateupeonding.paymentservice.model.entity.type.ProductType;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface PaymentTransactionsHistoryRepository extends ReactiveCrudRepository<PaymentTransactionsHistory, UUID> {

    Flux<PaymentTransactionsHistory> findAllByUserId(@Param("userId") UUID userId);

    Flux<PaymentTransactionsHistory> findAllByProductIdAndProductType(@Param("productId") UUID productId,
                                                                      @Param("productType") ProductType productType);

    Flux<Void> deleteAllByUserId(@Param("userId") UUID userId);

    Flux<Void> deleteAllByProductIdAndProductType(@Param("productId") UUID productId,
                                                  @Param("productType") ProductType productType);

}
