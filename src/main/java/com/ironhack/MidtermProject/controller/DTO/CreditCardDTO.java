package com.ironhack.MidtermProject.controller.DTO;


import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreditCardDTO {

    @NotNull(message = "Balance is required")
    private BigDecimal balance;

    @NotNull(message = "Primary Owner Id is required")
    private Long idPrimaryOwner;

    private Long idSecondaryOwner;

    @DecimalMax(value = "100000")
    @DecimalMin(value = "100")
    private BigDecimal creditLimit = new BigDecimal("100");

    @DecimalMax(value = "0.2")
    @DecimalMin(value = "0.1")
    private BigDecimal interestRate = new BigDecimal("0.2");


    public CreditCardDTO() {
    }

    public CreditCardDTO(@NotNull(message = "Balance is required") BigDecimal balance, @NotNull(message = "Primary Owner Id is required") Long idPrimaryOwner, Long idSecondaryOwner, @DecimalMax(value = "100000") @DecimalMin(value = "100", inclusive = false) BigDecimal creditLimit, @DecimalMax(value = "0.2", inclusive = false) @DecimalMin(value = "0.1") BigDecimal interestRate) {
        this.balance = balance;
        this.idPrimaryOwner = idPrimaryOwner;
        this.idSecondaryOwner = idSecondaryOwner;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getIdPrimaryOwner() {
        return idPrimaryOwner;
    }

    public void setIdPrimaryOwner(Long idPrimaryOwner) {
        this.idPrimaryOwner = idPrimaryOwner;
    }

    public Long getIdSecondaryOwner() {
        return idSecondaryOwner;
    }

    public void setIdSecondaryOwner(Long idSecondaryOwner) {
        this.idSecondaryOwner = idSecondaryOwner;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
