package com.ateupeonding.paymentservice.model;

import com.ateupeonding.paymentservice.model.dto.type.PaymentStatus;

import java.time.OffsetDateTime;

public class PaymentResult {

    private PaymentStatus status;
    private OffsetDateTime paymentDate;

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public OffsetDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(OffsetDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
