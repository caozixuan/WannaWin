package citi.vo;

public class Merchant {
    private String merchantID;
    private String name;
    private String description;
    private String address;
    private String logoURL;

    public Merchant(String merchantID, String name, String description, String address, String logoURL) {
        this.merchantID = merchantID;
        this.name = name;
        this.description = description;
        this.address = address;
        this.logoURL = logoURL;
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

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    @Override
    public String toString() {
        return "{\"merchantID\":" + merchantID + ",\"merchantName\":" + name + ",\"description\":" + description + ",\"logoURL\":" + logoURL + "}";
    }
}
