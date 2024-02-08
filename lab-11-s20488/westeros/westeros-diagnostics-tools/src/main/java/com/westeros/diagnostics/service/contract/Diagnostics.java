package com.westeros.diagnostics.service.contract;

import lombok.Data;

@Data
public class Diagnostics {
    private boolean isSuccess;
    private String name;
    private String errorMessage;
    private String description;

    public Diagnostics() {
    }

    public Diagnostics(boolean isSuccess, String name, String errorMessage, String description) {
        this.isSuccess = isSuccess;
        this.name = name;
        this.errorMessage = errorMessage;
        this.description = description;
    }
}
