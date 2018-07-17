package com.citiexchangeplatform.pointsleague.models;

public class HistoryExchangeItemModel {
    private int point;
    private double exchangePoint;
    private String date;

    public HistoryExchangeItemModel(int point, double exchangePoint, String date) {
        this.point = point;
        this.exchangePoint = exchangePoint;
        this.date = date;
    }

    public int getPoint() {
        return point;
    }

    public double getExchangePoint() {
        return exchangePoint;
    }

    public String getDate() {
        return date;
    }
}
