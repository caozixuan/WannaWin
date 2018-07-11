package citi.vo;

public class CardLogo {
    private String MerchantID;
    private String logoURL;

    public CardLogo(String merchantID, String logoURL) {
        MerchantID = merchantID;
        this.logoURL = logoURL;
    }

    public String getMerchantID() {
        return MerchantID;
    }

    public String getLogoURL() {
        return logoURL;
    }

}
