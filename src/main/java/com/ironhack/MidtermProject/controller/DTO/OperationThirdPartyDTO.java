package com.ironhack.MidtermProject.controller.DTO;

import com.ironhack.MidtermProject.enums.TransactionType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class OperationThirdPartyDTO {
    @NotNull(message = "Amount cannot be null")
    @Positive(message = "The amount must be positive")
    private BigDecimal amount;
    @NotNull (message = "Id cannot be null")
    private Long id;
    @NotNull (message = "Secretkey cannot be null")
    private String secretKey;
    @Enumerated(EnumType.STRING)
    @NotNull (message = "Transaction type cannot be null")
    private TransactionType transactionType;

    public OperationThirdPartyDTO() {
    }

    public OperationThirdPartyDTO(@NotNull(message = "Please, specify the amount to transfer") @Positive(message = "The amount must be positive") BigDecimal amount, @NotNull(message = "Please, specify the id of the account") Long id, @NotNull(message = "Please, specify the secretKey of the account") String secretKey, @NotNull TransactionType transactionType) {
        this.amount = amount;
        this.id = id;
        this.secretKey = secretKey;
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}

