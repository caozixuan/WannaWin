package com.citiexchangeplatform.pointsleague.models;

import java.util.List;

public class ExchangeModel {

    private Boolean choose;
    private String maxExchangePoint;
    private String exchangePoint;
    private String targetPoint;
    private String name;
    private double rate;
    private String logo;

    // 存储勾选框状态的map集合
    //private HashMap<Integer, Boolean> map = new HashMap<>();


    public ExchangeModel(Boolean choose, String general_point, String posses_point, Double rate, String logo_url, String name) {
        this.choose = choose;
        this.maxExchangePoint = general_point;
        this.exchangePoint = general_point;
        this.targetPoint = posses_point;
        this.rate = rate;
        this.logo = logo_url;
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Boolean getChoose() {
        return choose;
    }

    public void setChoose(Boolean choose) {
        this.choose = choose;
    }

    public String getMaxExchangePoint() {
        return maxExchangePoint;
    }

    public void setMaxExchangePoint(String maxExchangePoint) {
        this.maxExchangePoint = maxExchangePoint;
    }

    public String getExchangePoint() {
        return exchangePoint;
    }

    public void setExchangePoint(String exchangePoint) {
        this.exchangePoint = exchangePoint;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTargetPoint() {
        return targetPoint;
    }

    public void setTargetPoint(String targetPoint) {
        this.targetPoint = targetPoint;
    }
}
