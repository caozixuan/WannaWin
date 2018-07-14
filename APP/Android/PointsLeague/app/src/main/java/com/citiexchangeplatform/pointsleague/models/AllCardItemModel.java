package com.citiexchangeplatform.pointsleague.models;


public class AllCardItemModel {
    private String name;
    private String logoURL;
    private int point;
    private double proportion;

    public AllCardItemModel(String name, String logoURL, int point, double proportion) {
        this.name = name;
        this.logoURL = logoURL;
        this.point = point;
        this.proportion = proportion;
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

    public double getExchangePoint(){
        return getPoint()/getProportion();
    }
}
