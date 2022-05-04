package com.ateupeonding.paymentservice.model.dto;

import com.ateupeonding.paymentservice.model.dto.type.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponse implements Serializable {

    private UUID userId;
    private UUID productId;
    private String productType;
    private PaymentStatus status;

    private OffsetDateTime createdTimestamp;


    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public OffsetDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(OffsetDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
}
