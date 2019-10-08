package com.maruszka.model.enums;

public enum IngredientType {
    MALT("Malt"),
    HOP("Hop"),
    YEAST("Yeast"),
    ADDITIVE("Additive");

    private String description;

    IngredientType(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
