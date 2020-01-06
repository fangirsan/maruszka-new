package com.maruszka.model.enums;

public enum LauteringType {
    SIEVE("Sieve"),
    TUBE("Tube");

    private String description;

    LauteringType(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
