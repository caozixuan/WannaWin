package citi.vo;

import com.google.gson.annotations.Expose;

public class Merchant {
    private String merchantID;
    private String name;
    @Expose(serialize = false)
    private String password;
    private String description;
    private String address;
    private String logoURL;
    private double proportion;

    public Merchant(String merchantID, String name, String password, String description, String address, String logoURL, double proportion) {
        this.merchantID = merchantID;
        this.name = name;
        this.password = password;
        this.description = description;
        this.address = address;
        this.logoURL = logoURL;
        this.proportion = proportion;
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

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

}
