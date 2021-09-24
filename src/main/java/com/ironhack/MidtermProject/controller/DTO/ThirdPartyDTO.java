package com.ironhack.MidtermProject.controller.DTO;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ThirdPartyDTO {

    @NotNull(message = "Name can not be null")
    private String name;

    private final Integer hashedKey = hashCode();

    public ThirdPartyDTO() {
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThirdPartyDTO that = (ThirdPartyDTO) o;
        return getName().equals(that.getName()) && getHashedKey().equals(that.getHashedKey());
    }

    public int hashCode() {
        return Objects.hash(getName());
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getHashedKey() {
        return hashedKey;
    }
}
