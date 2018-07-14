package com.citiexchangeplatform.pointsleague.models;

public class MerchantInfoModel {
    private String name;
    private String type; //enum or int
    private String logoURL;
    private String address;
    private String activityContent;

    public MerchantInfoModel(String name, String type, String logoURL, String address, String activityContent){
        this.name = name;
        this.type = type;
        this.logoURL = logoURL;
        this.address = address;
        this.activityContent = activityContent;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public String getAddress() {
        return address;
    }

    public String getActivityContent() {
        return activityContent;
    }
}
