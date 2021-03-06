package citi.funcModule.mscard;

public class BriefCard implements  Comparable<BriefCard>{
    private String merchantID;
    private String merchantLogoURL;
    private String merchantName;
    private int points;
    private double proportion;
    private String logoURL;
    private int cardStyle;

    public BriefCard(String merchantID, String merchantLogoURL, String merchantName, int points, double proportion, String logoURL, int cardStyle) {
        this.merchantID = merchantID;
        this.merchantLogoURL = merchantLogoURL;
        this.merchantName = merchantName;
        this.points = points;
        this.proportion = proportion;
        this.logoURL = logoURL;
        this.cardStyle = cardStyle;
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

    public String getLogoURL() {
        return logoURL;
    }

    public double getProportion() {
        return proportion;
    }

    @Override
    public int compareTo(BriefCard o) {
        if(o.points*o.proportion-this.points*this.proportion>0)
            return 1;
        return -1;
    }
}
