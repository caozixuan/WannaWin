package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.content.SharedPreferences;

public class LogStateInfo {
    static private LogStateInfo logStateInfo = null;

    static private SharedPreferences sharedPreferences;

    private LogStateInfo(Context context){
        sharedPreferences = context.getSharedPreferences("LoginStateInfo", Context.MODE_PRIVATE);
    }

    static public LogStateInfo getInstance(Context context) {
        if(logStateInfo == null)
            logStateInfo = new LogStateInfo(context);
        return logStateInfo;
    }

    public boolean isLogin(){
        return sharedPreferences.getBoolean("isLogin",false);
    }

    public LogStateInfo login(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin", true);
        editor.apply();
        return logStateInfo;
    }

    public LogStateInfo logout(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin", false);
        editor.apply();
        return logStateInfo;
    }

    public LogStateInfo setAccount(String account){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("account", account);
        editor.apply();
        return logStateInfo;
    }

    public String getAccount(){
        return sharedPreferences.getString("account", "load error");
    }

    public LogStateInfo setGeneralPoint(int generalPoint){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("generalPoint", generalPoint);
        editor.apply();
        return logStateInfo;
    }

    public int getGeneralPoint(){ return sharedPreferences.getInt("generalPoint",0); }

    public LogStateInfo setAvailablePoints(int availablePoints){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("availablePoints", availablePoints);
        editor.apply();
        return logStateInfo;
    }

    public int getAvailablePoints(){ return sharedPreferences.getInt("availablePoints",0); }

    public LogStateInfo setUserID(String userID){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userID", userID);
        editor.apply();
        return logStateInfo;
    }

    public String getUserID(){ return sharedPreferences.getString("userID",null); }

}
