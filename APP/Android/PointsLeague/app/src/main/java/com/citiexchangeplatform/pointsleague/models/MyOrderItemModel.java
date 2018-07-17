package com.citiexchangeplatform.pointsleague.models;

public class MyOrderItemModel {
    String merchantName;
    String description;
    String date;

    public MyOrderItemModel(String merchantName, String description, String date) {
        this.merchantName = merchantName;
        this.description = description;
        this.date = date;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
