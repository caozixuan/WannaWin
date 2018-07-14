package citi.vo;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.Map;

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
    private double proportion;
    private String activityTheme;
    private String activityDescription;
    private String cardType;

    public enum CardType {
        cardNum, phoneNum, cardNumWithPassword, phoneNumWithPassword;

        static Map<String, CardType> enumMap1 = new HashMap<>();
        static Map<CardType, String> enumMap2 = new HashMap<>();

        static {
            enumMap1.put("cardNum", cardNum);
            enumMap1.put("phoneNum", phoneNum);
            enumMap1.put("cardNumWithPassword", cardNumWithPassword);
            enumMap1.put("phoneNumWithPassword", phoneNumWithPassword);

            enumMap2.put(cardNum, "cardNum");
            enumMap2.put(phoneNum, "phoneNum");
            enumMap2.put(cardNumWithPassword, "cardNumWithPassword");
            enumMap2.put(phoneNumWithPassword, "phoneNumWithPassword");
        }

        public static CardType getCardType(String cardType) {
            return enumMap1.get(cardType);
        }

        public static String getCardTypeString(CardType cardType) {
            return enumMap2.get(cardType);
        }

    }

    public Merchant(String merchantID, String name, String password, String description, String cardDescription, String address, String merchantLogoURL, String cardLogoURL, double proportion, String activityTheme, String activityDescription, String cardType) {
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
        this.cardType = cardType;
    }

    public Merchant(String merchantID, String name, String password, String description, String cardDescription, String address, String merchantLogoURL, String cardLogoURL, double proportion, String activityTheme, String activityDescription, CardType cardType) {
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
        this.cardType = CardType.getCardTypeString(cardType);
    }

    public String getCardType() {
        return cardType;
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

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) {
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