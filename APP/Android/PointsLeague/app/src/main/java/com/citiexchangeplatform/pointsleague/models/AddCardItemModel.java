package com.citiexchangeplatform.pointsleague.models;

public class AddCardItemModel {
    private String name;
    private String description;
    private String logoURL;

    public AddCardItemModel(String name, String description, String logoURL) {
        this.name = name;
        this.description = description;
        this.logoURL = logoURL;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLogoURL() {
        return logoURL;
    }
}
