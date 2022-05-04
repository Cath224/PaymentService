package com.ateupeonding.paymentservice.model.entity;


import com.ateupeonding.paymentservice.model.entity.type.ProductType;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;


public class PaymentTransactionsHistory {

    @Id
    private UUID id;
    private UUID userId;
    private ProductType productType;
    private UUID productId;
    private BigDecimal amount;
    private OffsetDateTime createdTimestamp;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OffsetDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(OffsetDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
}
