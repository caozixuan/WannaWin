package citi.dao;

import citi.vo.Merchant;
import com.google.gson.annotations.Expose;

/**
 * Created by zhong on 2018/7/11 14:50
 */
public class MerchantDAO {
    private String merchantID;
    private String name;
    private String password;
    private String description;
    private String address;
    private String logoURL;

    public MerchantDAO(String merchantID, String name,String password, String description, String address, String logoURL) {
        this.merchantID = merchantID;
        this.name = name;
        this.password=password;
        this.description = description;
        this.address = address;
        this.logoURL = logoURL;
    }

    public Merchant toMerchant(){
        return new Merchant(merchantID,name,description,address,logoURL);
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
