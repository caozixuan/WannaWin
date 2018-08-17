package com.citiexchangeplatform.pointsleague.models;

public class DetailActivityItemModel {
    private String activityID;
    private String name;
    private String logoURL;

    public DetailActivityItemModel(String activityID, String name, String logoURL) {
        this.activityID = activityID;
        this.name = name;
        this.logoURL = logoURL;
    }

    public String getActivityID() {
        return activityID;
    }

    public String getName() {
        return name;
    }

    public String getLogoURL() {
        return logoURL;
    }
}
