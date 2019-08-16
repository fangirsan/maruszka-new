package com.maruszka.model.enums;

public enum ProducerType {
    Malt("Malt"),
    Yeast("Yeast"),
    Hop("Hop");

    private String description;

    ProducerType(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
