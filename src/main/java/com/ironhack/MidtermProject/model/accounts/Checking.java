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
public class Checking extends Account {

    private String secretKey;

    @Embedded
    @Valid
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_monthly_maintenance_fee")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_monthly_maintenance_fee"))
    })
    private final Money monthlyMaintenanceFee = new Money(new BigDecimal("12"));

    @Embedded
    @Valid
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_minimum_balance")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_minimum_balance"))
    })
    private final Money minimumBalance = new Money(new BigDecimal("250"));

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public Checking() {
    }

    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate dateOfCreation, List<Transaction> receivedTransactions, List<Transaction> sentTransactions, String secretKey, Status status) {
        super(balance, primaryOwner, secondaryOwner, dateOfCreation, receivedTransactions, sentTransactions);
        this.secretKey = secretKey;
        this.status = status;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}