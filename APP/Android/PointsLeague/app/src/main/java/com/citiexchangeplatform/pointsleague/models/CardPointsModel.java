package com.citiexchangeplatform.pointsleague.models;

public class CardPointsModel {
    private String name;
    private String merchantID;
    private int points;
    private double proportion;
    private int cardStyle;

    public CardPointsModel(String name, String merchantID, int points, double proportion, int cardStyle) {
        this.name = name;
        this.merchantID = merchantID;
        this.points = points;
        this.proportion = proportion;
        this.cardStyle = cardStyle;
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

    public int getCardStyle() {
        return cardStyle;
    }
}
