package com.citiexchangeplatform.pointsleague.models;


public class AllCardItemModel {
    private String merchantID;
    private String name;
    private String logoURL;
    private int point;
    private double proportion;
    private int cardStyle;

    public AllCardItemModel(String merchantID, String name, String logoURL, int point, double proportion, int cardStyle) {
        this.merchantID = merchantID;
        this.name = name;
        this.logoURL = logoURL;
        this.point = point;
        this.proportion = proportion;
        this.cardStyle = cardStyle;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public String getName() {
        return name;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public int getPoint() {
        return point;
    }

    public double getProportion() {
        return proportion;
    }

    public int getCardStyle() {
        return cardStyle;
    }
}
