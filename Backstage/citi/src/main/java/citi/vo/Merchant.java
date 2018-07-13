package citi.vo;

import com.google.gson.annotations.Expose;

public class Merchant {
    private String merchantID;
    private String name;
    @Expose(serialize = false)
    private String password;
    private String description;
    private String cardDescription;
    private String address;
    private String merchantLogoURL;
    private String cardLogoURL;
    private Double proportion;
    private String activityTheme;
    private String activityDescription;

    public Merchant(String merchantID, String name, String password, String description, String cardDescription, String address, String merchantLogoURL, String cardLogoURL, double proportion, String activityTheme, String activityDescription) {
        this.merchantID = merchantID;
        this.name = name;
        this.password = password;
        this.description = description;
        this.cardDescription = cardDescription;
        this.address = address;
        this.merchantLogoURL = merchantLogoURL;
        this.cardLogoURL = cardLogoURL;
        this.proportion = proportion;
        this.activityTheme = activityTheme;
        this.activityDescription = activityDescription;
    }

    public String getActivityTheme() {
        return activityTheme;
    }

    public void setActivityTheme(String activityTheme) {
        this.activityTheme = activityTheme;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public Merchant() {

    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public String getCardLogoURL() {
        return cardLogoURL;
    }

    public void setCardLogoURL(String cardLogoURL) {
        this.cardLogoURL = cardLogoURL;
    }

    public Double getProportion() {
        if (proportion == null) {
            return 0.0;
        }
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMerchantLogoURL() {
        return merchantLogoURL;
    }

    public void setMerchantLogoURL(String logoURL) {
        this.merchantLogoURL = logoURL;
    }

}
