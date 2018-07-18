package citi.vo;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.Map;

public class Merchant {
    @Expose
    private String merchantID;
    @Expose
    private String name;
    @Expose(serialize = false)
    private String password;
    @Expose
    private String description;
    @Expose
    private String cardDescription;
    @Expose
    private String address;
    @Expose
    private String merchantLogoURL;
    @Expose
    private String cardLogoURL;
    @Expose
    private double proportion;
    @Expose
    private String activityTheme;
    @Expose
    private String activityDescription;
    @Expose
    private String cardType;
    @Expose
    private String businessType;

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


    public enum BusinessType {
        normal, catering, trip, bank;

        static Map<String, BusinessType> enumMap1 = new HashMap<>();
        static Map<BusinessType, String> enumMap2 = new HashMap<>();

        static {
            enumMap1.put("normal", normal);
            enumMap1.put("catering", catering);
            enumMap1.put("trip", trip);
            enumMap1.put("bank", bank);

            enumMap2.put(normal, "normal");
            enumMap2.put(catering, "catering");
            enumMap2.put(trip, "trip");
            enumMap2.put(bank, "bank");
        }

        public static BusinessType getBusinessType(String businessType) {
            return enumMap1.get(businessType);
        }

        public static String getBusinessTypeString(BusinessType businessType) {
            return enumMap2.get(businessType);
        }

    }


    //for DB
    public Merchant(String merchantID, String name, String password, String description, String cardDescription, String address, String merchantLogoURL, String cardLogoURL, double proportion, String activityTheme, String activityDescription, String cardType, String businessType) {
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
        this.businessType = businessType;
    }


    //for programmer
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
        this.businessType = BusinessType.getBusinessTypeString(BusinessType.normal);
    }

    public CardType getCardType() {
        return CardType.getCardType(cardType);
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

    public BusinessType getBusinessType() {
        return BusinessType.getBusinessType(businessType);
    }

    public void setCardType(CardType cardType) {
        this.cardType = CardType.getCardTypeString(cardType);
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = BusinessType.getBusinessTypeString(businessType);
    }

}