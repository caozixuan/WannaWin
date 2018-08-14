package com.citiexchangeplatform.pointsleague.models;

//"merchantName":"KFC",
//"orderID":"0002",
//"originalPrice":400.0,
//"priceAfter":333.0,
//"pointsNeeded":200.0,
//"userID":"5503b50f-2312-4156-92b3-ec6dcea74656",
//"state":"SUCCESS",
//"merchantID":"00002",
//"time":"Jul 9, 2018 3:30:00 PM"
public class OrderItemModel {
    private String merchantName;
    private double originalPrice;
    private double priceAfter;
    private double pointsNeeded;
    private String logoURL;
    private String date;

    public OrderItemModel(String merchantName, double originalPrice, double priceAfter, double pointsNeeded, String logoURL, String date) {
        this.merchantName = merchantName;
        this.originalPrice = originalPrice;
        this.priceAfter = priceAfter;
        this.pointsNeeded = pointsNeeded;
        this.logoURL = logoURL;
        this.date = date;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getPriceAfter() {
        return priceAfter;
    }

    public void setPriceAfter(double priceAfter) {
        this.priceAfter = priceAfter;
    }

    public double getPointsNeeded() {
        return pointsNeeded;
    }

    public void setPointsNeeded(double pointsNeeded) {
        this.pointsNeeded = pointsNeeded;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
