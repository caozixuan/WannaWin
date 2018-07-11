package citi.dao;

public class MerchantDAO {

    private String merchantID;
    private String name;
    private String password;
    private String description;
    private String address;
    private String logoURL;

    public MerchantDAO(String merchantID, String name, String password, String description, String address, String logoURL) {
        this.merchantID = merchantID;
        this.name = name;
        this.password = password;
        this.description = description;
        this.address = address;
        this.logoURL = logoURL;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getLogoURL() {
        return logoURL;
    }
    
}
