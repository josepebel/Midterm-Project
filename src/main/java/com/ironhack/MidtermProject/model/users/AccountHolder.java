package com.ironhack.MidtermProject.model.users;


import com.ironhack.MidtermProject.model.supportive.Address;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class AccountHolder extends User{

    private LocalDate dateOfBirth;

    @Embedded
    private Address primaryAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="street", column=@Column(name="mailing_street")),
            @AttributeOverride(name="city", column=@Column(name="mailing_city")),
            @AttributeOverride(name="country", column=@Column(name="mailing_country")),
            @AttributeOverride(name="zipCode", column=@Column(name="mailing_zip"))
    })
    private Address mailingAddress;


    public AccountHolder() {
    }


    public AccountHolder(String username, String password, String name, LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress) {
        super(username, password, name);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}
