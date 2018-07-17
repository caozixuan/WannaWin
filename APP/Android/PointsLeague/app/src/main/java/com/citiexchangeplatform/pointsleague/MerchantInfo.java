package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

public class MerchantInfo {

    static private MerchantInfo merchantInfo = null;
    static private SharedPreferences sharedPreferences;


    private MerchantInfo(Context context){
        sharedPreferences = context.getSharedPreferences("MerchantInfoModel", Context.MODE_PRIVATE);
    }

    static public MerchantInfo getInstance(Context context) {
        if(merchantInfo == null)
            merchantInfo = new MerchantInfo(context);
        return merchantInfo;
    }


    public String getMerchantID() {
        return sharedPreferences.getString("merchantID", "load error");
    }

    public MerchantInfo setMerchantID(String merchantID) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("merchantID", merchantID);
        editor.apply();
        return merchantInfo;
    }

    public String getName() {
        return sharedPreferences.getString("totalPoints", "load error");
    }

    public MerchantInfo setName(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("totalPoints", name);
        editor.apply();
        return merchantInfo;
    }

    public String getDescription() {
        return sharedPreferences.getString("description", "load error");
    }

    public MerchantInfo setDescription(String description) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("description", description);
        editor.apply();
        return merchantInfo;
    }

    public String getLogoURL() {
        return sharedPreferences.getString("logoURL", "load error");
    }

    public MerchantInfo setLogoURL(String logoURL) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("logoURL", logoURL);
        editor.apply();
        return merchantInfo;
    }
    public String getAddress() {
        return sharedPreferences.getString("address", "load error");
    }

    public MerchantInfo setAddress(String address) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("address", address);
        editor.apply();
        return merchantInfo;
    }


}
