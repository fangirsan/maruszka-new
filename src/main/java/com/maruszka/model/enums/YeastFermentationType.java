package com.maruszka.model.enums;

public enum YeastFermentationType {

    TOP("Top fermentation"),
    BOTTOM("Bottom fermentation");

    private String description;

    YeastFermentationType(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
