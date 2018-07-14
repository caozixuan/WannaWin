package citi.mscard;

public class BriefCard {
    private String merchantID;
    private String merchantLogoURL;
    private String merchantName;
    private int points;
    private double proportion;

    public BriefCard(String merchantID, String merchantLogoURL, String merchantName, int points, double proportion) {
        this.merchantID = merchantID;
        this.merchantLogoURL = merchantLogoURL;
        this.merchantName = merchantName;
        this.points = points;
        this.proportion = proportion;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public String getMerchantLogoURL() {
        return merchantLogoURL;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public int getPoints() {
        return points;
    }

    public double getProportion() {
        return proportion;
    }
}
