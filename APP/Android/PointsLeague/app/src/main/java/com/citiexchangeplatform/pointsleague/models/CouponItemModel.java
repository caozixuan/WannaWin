package com.citiexchangeplatform.pointsleague.models;

public class CouponItemModel {
    String merchantName;
    String needPoints;
    String description;
    String exchangeDate;
    String validityDate;
    String logoURL;
    String itemID;

    public CouponItemModel(String merchantName, String needPoints, String description, String exchangeDate, String validityDate, String logoURL, String itemID) {
        this.merchantName = merchantName;
        this.needPoints = needPoints;
        this.description = description;
        this.exchangeDate = exchangeDate;
        this.validityDate = validityDate;
        this.logoURL = logoURL;
        this.itemID = itemID;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getNeedPoints() {
        return needPoints;
    }

    public void setNeedPoints(String needPoints) {
        this.needPoints = needPoints;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(String exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public String getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }
}
