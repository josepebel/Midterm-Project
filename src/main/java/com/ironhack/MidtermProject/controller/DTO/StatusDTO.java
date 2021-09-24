package com.ironhack.MidtermProject.controller.DTO;

import com.ironhack.MidtermProject.enums.Status;

import javax.validation.constraints.NotNull;

public class StatusDTO {
    @NotNull
    private Status status;
    public StatusDTO(Status status) {
        this.status = status;
    }
    public StatusDTO() {
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}

