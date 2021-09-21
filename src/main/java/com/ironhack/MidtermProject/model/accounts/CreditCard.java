package com.ironhack.MidtermProject.model.accounts;


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
public class CreditCard extends Account {

    @Embedded
    @Valid
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_credit_limit")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_credit_limit"))
    })
    private Money creditLimit;
    private BigDecimal interestRate;
    private LocalDate dateOfLastAccess;

    public CreditCard() {
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate dateOfCreation, List<Transaction> receivedTransactions, List<Transaction> sentTransactions, @Valid Money creditLimit, BigDecimal interestRate, LocalDate dateOfLastAccess) {
        super(balance, primaryOwner, secondaryOwner, dateOfCreation, receivedTransactions, sentTransactions);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.dateOfLastAccess = dateOfLastAccess;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDate getDateOfLastAccess() {
        return dateOfLastAccess;
    }

    public void setDateOfLastAccess(LocalDate dateOfLastAccess) {
        this.dateOfLastAccess = dateOfLastAccess;
    }

}