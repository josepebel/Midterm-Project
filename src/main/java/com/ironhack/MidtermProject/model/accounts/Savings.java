package com.ironhack.MidtermProject.model.accounts;


import com.ironhack.MidtermProject.enums.Status;
import com.ironhack.MidtermProject.model.supportive.Money;
import com.ironhack.MidtermProject.model.supportive.Transaction;
import com.ironhack.MidtermProject.model.users.AccountHolder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DynamicUpdate
public class Savings extends Account {

    private BigDecimal interestRate;
    private String secretKey;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Embedded
    @Valid
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_minimum_balance")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_minimum_balance"))
    })
    private Money minimumBalance;
    private LocalDate dateOfLastAccess;

    public Savings() {
    }

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate dateOfCreation, List<Transaction> receivedTransactions, List<Transaction> sentTransactions, BigDecimal interestRate, String secretKey, Status status, @Valid Money minimumBalance, LocalDate dateOfLastAccess) {
        super(balance, primaryOwner, secondaryOwner, dateOfCreation, receivedTransactions, sentTransactions);
        this.interestRate = interestRate;
        this.secretKey = secretKey;
        this.status = status;
        this.minimumBalance = minimumBalance;
        this.dateOfLastAccess = dateOfLastAccess;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public LocalDate getDateOfLastAccess() {
        return dateOfLastAccess;
    }

    public void setDateOfLastAccess(LocalDate dateOfLastAccess) {
        this.dateOfLastAccess = dateOfLastAccess;
    }

}