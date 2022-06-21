package com.ateupeonding.paymentservice.query;

import com.ateupeonding.paymentservice.configuration.MessagingConfiguration;
import com.ateupeonding.paymentservice.model.PaymentResult;
import com.ateupeonding.paymentservice.model.dto.PaymentRequest;
import com.ateupeonding.paymentservice.model.dto.PaymentResponse;
import com.ateupeonding.paymentservice.model.dto.type.PaymentStatus;
import com.ateupeonding.paymentservice.model.entity.PaymentTransactionsHistory;
import com.ateupeonding.paymentservice.service.api.PaymentService;
import com.ateupeonding.paymentservice.service.api.PaymentTransactionsHistoryService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Component
public class PaymentRequestListener {

    @Autowired
    private PaymentTransactionsHistoryService paymentTransactionsHistoryService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = MessagingConfiguration.PAYMENT_REQUEST_QUEUE)
    public void listenPaymentRequest(PaymentRequest paymentRequest) {
        BigDecimal amount = paymentRequest.getAmount();
        Mono<PaymentResult> paymentResultMono = paymentService.processPayment(amount);
        paymentResultMono
                .flatMap((el) -> {
                    if (PaymentStatus.PAID.equals(el.getStatus())) {
                        PaymentTransactionsHistory history = createHistory(paymentRequest);
                        return paymentTransactionsHistoryService.create(history)
                                .map((historyResult) -> {
                                    PaymentResponse paymentResponse = new PaymentResponse();
                                    paymentResponse.setProductId(historyResult.getProductId());
                                    paymentResponse.setProductType(String.valueOf(historyResult.getProductType()));
                                    paymentResponse.setCreatedTimestamp(historyResult.getCreatedTimestamp());
                                    paymentResponse.setUserId(historyResult.getUserId());
                                    paymentResponse.setStatus(el.getStatus());
                                    return paymentResponse;
                                });
                    } else {
                        return Mono.fromSupplier(() -> {
                            PaymentResponse paymentResponse = new PaymentResponse();
                            paymentResponse.setProductId(paymentRequest.getProductId());
                            paymentResponse.setProductType(String.valueOf(paymentRequest.getProductType()));
                            paymentResponse.setCreatedTimestamp(el.getPaymentDate());
                            paymentResponse.setUserId(paymentRequest.getUserId());
                            paymentResponse.setStatus(el.getStatus());
                            return paymentResponse;
                        });
                    }
                })
                .subscribe(this::sendResponse);
    }

    private void sendResponse(PaymentResponse paymentResponse) {
        rabbitTemplate.convertAndSend(MessagingConfiguration.PAYMENT_RESPONSE_QUEUE, paymentResponse);
    }

    private PaymentTransactionsHistory createHistory(PaymentRequest paymentRequest) {
        PaymentTransactionsHistory paymentTransactionsHistory = new PaymentTransactionsHistory();
        paymentTransactionsHistory.setProductId(paymentRequest.getProductId());
        paymentTransactionsHistory.setProductType(paymentRequest.getProductType());
        paymentTransactionsHistory.setAmount(paymentRequest.getAmount());
        paymentTransactionsHistory.setUserId(paymentRequest.getUserId());
        return paymentTransactionsHistory;
    }

}
