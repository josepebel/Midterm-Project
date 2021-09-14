package com.ironhack.MidtermProject.model.supportive;


import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;

    private String city;

    private String country;

    private Integer zipCode;

    public Address() {
    }

    public Address(String street, String city, String country, Integer zipCode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }
}
