package com.citiexchangeplatform.pointsleague.models;

import java.util.List;

public class ExchangeModel {

    private Boolean choose;

    private int maxExchangePoint;
    private int exchangePoint;
    private double targetPoint;
    private String name;
    private String merchantID;
    private double rate;
    private String logo;

    // 存储勾选框状态的map集合
    //private HashMap<Integer, Boolean> map = new HashMap<>();


    public ExchangeModel(Boolean choose, int general_point, double targetPoint, String name, String merchantID, double rate, String logo) {
        this.choose = choose;
        this.maxExchangePoint = general_point;
        this.exchangePoint = general_point;
        this.targetPoint = targetPoint;
        this.name = name;
        this.merchantID = merchantID;
        this.rate = rate;
        this.logo = logo;
    }

    public Boolean getChoose() {
        return choose;
    }

    public void setChoose(Boolean choose) {
        this.choose = choose;
    }

    public int getMaxExchangePoint() {
        return maxExchangePoint;
    }

    public void setMaxExchangePoint(int maxExchangePoint) {
        this.maxExchangePoint = maxExchangePoint;
    }

    public int getExchangePoint() {
        return exchangePoint;
    }

    public void setExchangePoint(int exchangePoint) {
        this.exchangePoint = exchangePoint;
    }

    public double getTargetPoint() {
        return targetPoint;
    }

    public void setTargetPoint(double targetPoint) {
        this.targetPoint = targetPoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
