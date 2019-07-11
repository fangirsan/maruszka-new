package com.maruszka.model.Enums;

public enum YeastType {
	Dry("Dry yeast"),
	Liquid("Liquid yeast");

	private String description;

	YeastType(final String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
