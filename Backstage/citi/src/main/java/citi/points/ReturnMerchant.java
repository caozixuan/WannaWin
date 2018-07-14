package citi.points;

public class ReturnMerchant {
    private String merchantID;
    private String merchantName;
    private String merchantLogoURL;
    private String reason;

    public ReturnMerchant(String merchantID, String merchantName, String merchantLogoURL, String reason) {
        this.merchantID = merchantID;
        this.merchantName = merchantName;
        this.merchantLogoURL = merchantLogoURL;
        this.reason = reason;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getMerchantLogoURL() {
        return merchantLogoURL;
    }

    public String getReason() {
        return reason;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setMerchantLogoURL(String merchantLogoURL) {
        this.merchantLogoURL = merchantLogoURL;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
