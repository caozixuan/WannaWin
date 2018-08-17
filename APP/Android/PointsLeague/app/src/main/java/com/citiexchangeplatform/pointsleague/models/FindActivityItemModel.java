package com.citiexchangeplatform.pointsleague.models;

public class FindActivityItemModel {
    private String itemID;
    private String name;
    private String logoURL;

    public FindActivityItemModel(String itemID, String name, String logoURL) {
        this.itemID = itemID;
        this.name = name;
        this.logoURL = logoURL;
    }

    public String getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public String getLogoURL() {
        return logoURL;
    }
}
