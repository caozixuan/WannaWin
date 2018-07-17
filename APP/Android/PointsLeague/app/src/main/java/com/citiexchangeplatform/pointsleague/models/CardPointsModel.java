package com.citiexchangeplatform.pointsleague.models;

public class CardPointsModel {
    private String name;
    private int points;
    private double proportion;
    private String cardLogoURL;

    public CardPointsModel(String name, int points, double proportion, String cardLogoURL) {
        this.name = name;
        this.points = points;
        this.proportion = proportion;
        this.cardLogoURL = cardLogoURL;
    }

    public String getName() {
        return name;
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
