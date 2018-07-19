package com.citiexchangeplatform.pointsleague.models;

public class DetailFindItemModel {
    private String itemID;
    private String name;
    private String time;
    private String description;
    private String logoURL;
    private int points;

    public DetailFindItemModel(String itemID, String name, String time, String description, String logoURL, int points) {
        this.itemID = itemID;
        this.name = name;
        this.time = time;
        this.description = description;
        this.logoURL = logoURL;
        this.points = points;
    }

    public String getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public int getPoints() {
        return points;
    }
}
