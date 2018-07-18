package com.citiexchangeplatform.pointsleague.models;

public class FindItemModel {
    private String name;
    private String merchantID;
    private String logoURL;
    private String type;
    private String description;

    public FindItemModel(String name, String merchantID, String logoURL, String type, String description) {
        this.name = name;
        this.merchantID = merchantID;
        this.logoURL = logoURL;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getMerchantID() {
        return merchantID;
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
