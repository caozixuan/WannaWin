package com.citiexchangeplatform.pointsleague.models;

public class HistoryExchangeItemModel {
    private int point;
    private int exchangePoint;
    private String date;

    public HistoryExchangeItemModel(int point, int exchangePoint, String date) {
        this.point = point;
        this.exchangePoint = exchangePoint;
        this.date = date;
    }

    public int getPoint() {
        return point;
    }

    public int getExchangePoint() {
        return exchangePoint;
    }

    public String getDate() {
        return date;
    }
}
