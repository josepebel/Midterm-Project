package com.ironhack.MidtermProject.controller.DTO;

import com.ironhack.MidtermProject.model.supportive.Money;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class TransactionDTO {

    @NotNull(message = "Origen Account Id can not be null")
    private Long origenAccountId;
    @NotNull(message = "Destination Account Id can not be null")
    private Long destinationAccountId;
    @NotNull(message = "Description can not be null")
    private String description;
    @NotNull(message = "Amount can not be null")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "transaction_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "transaction_currency"))
    })
    private Money amount;
    private Date transactionDate;
    @NotNull(message = "Name owner's destination account can not be null")
    private String nameOwnerDestinationAccount;

    public TransactionDTO(){
    }

    public TransactionDTO(@NotNull Long origenAccountId, @NotNull Long destinationAccountId, @NotNull String description, @NotNull Money amount, String nameOwnerDestinationAccount) {
        this.origenAccountId = origenAccountId;
        this.destinationAccountId = destinationAccountId;
        this.description = description;
        this.amount = amount;
        this.transactionDate = new Date();
        this.nameOwnerDestinationAccount = nameOwnerDestinationAccount;
    }

    public Long getOrigenAccountId() {
        return origenAccountId;
    }
    public void setOrigenAccountId(Long origenAccountId) {
        this.origenAccountId = origenAccountId;
    }
    public Long getDestinationAccountId() {
        return destinationAccountId;
    }
    public void setDestinationAccountId(Long destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Money getAmount() {
        return amount;
    }
    public void setAmount(Money amount) {
        this.amount = amount;
    }
    public Date getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    public String getNameOwnerDestinationAccount() {
        return nameOwnerDestinationAccount;
    }
    public void setNameOwnerDestinationAccount(String nameOwnerDestinationAccount) {
        this.nameOwnerDestinationAccount = nameOwnerDestinationAccount;
    }
}
