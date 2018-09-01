package com.citiexchangeplatform.pointsleague.models;

public class FindSearchModel {

    private String name;
    private String id;
    private String logoURL;
    private String type;
    private String description;

    public FindSearchModel(String name, String id, String logoURL, String type, String description) {
        this.name = name;
        this.id = id;
        this.logoURL = logoURL;
        this.type = type;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
