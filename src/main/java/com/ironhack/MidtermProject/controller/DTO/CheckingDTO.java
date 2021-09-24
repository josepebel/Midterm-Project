package com.ironhack.MidtermProject.controller.DTO;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CheckingDTO {

    @NotNull(message = "Balance is required")
    private BigDecimal balance;

    @NotNull(message = "Primary Owner Id is required")
    private Long idPrimaryOwner;

    private Long idSecondaryOwner;

    @NotNull(message = "Secret key can not be null")
    private String secretKey;


    public CheckingDTO() {
    }

    public CheckingDTO(@NotNull(message = "Balance is required") BigDecimal balance, @NotNull(message = "Primary Owner Id is required") Long idPrimaryOwner, Long idSecondaryOwner, @NotNull(message = "Secret key can not be null") String secretKey) {
        this.balance = balance;
        this.idPrimaryOwner = idPrimaryOwner;
        this.idSecondaryOwner = idSecondaryOwner;
        this.secretKey = secretKey;
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

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
