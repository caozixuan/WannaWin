package com.citiexchangeplatform.pointsleague.models;

public class CardPointsModel {
    private String name;
    private String merchantID;
    private int points;
    private double proportion;
    private String cardLogoURL;

    public CardPointsModel(String name, String merchantID, int points, double proportion, String cardLogoURL) {
        this.name = name;
        this.merchantID = merchantID;
        this.points = points;
        this.proportion = proportion;
        this.cardLogoURL = cardLogoURL;
    }

    public String getName() {
        return name;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public int getPoints() {
        return points;
    }

    public double getProportion() {
        return proportion;
    }

    public String getCardLogoURL() {
        return cardLogoURL;
    }
}
