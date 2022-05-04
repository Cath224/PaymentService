package com.ateupeonding.paymentservice.mapper;

import com.ateupeonding.paymentservice.model.dto.PaymentTransactionsHistoryDto;
import com.ateupeonding.paymentservice.model.entity.PaymentTransactionsHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentTransactionsHistoryMapper {

    PaymentTransactionsHistoryDto toDto(PaymentTransactionsHistory entity);


    PaymentTransactionsHistory toEntity(PaymentTransactionsHistoryDto dto);

}
