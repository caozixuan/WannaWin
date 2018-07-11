package citi.vo;

import com.google.gson.annotations.Expose;

import java.util.UUID;

/**
 * 重构：刘钟博
 */
public class User {
    private String userID;
    private String citiCardID;
    @Expose(serialize = false)
    private String password;
    private String phoneNum;
    private double generalPoints;
    private double availablePoints;
    private String rewardLinkCode;

    public User(String phoneNum,String password) {
        userID=UUID.randomUUID().toString().toLowerCase();
        citiCardID="";
        this.password = password;
        this.phoneNum = phoneNum;
        generalPoints=0;
        availablePoints=0;
        rewardLinkCode="";
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public double getGeneralPoints() {
        return generalPoints;
    }

    public void setGeneralPoints(double generalPoints) {
        this.generalPoints = generalPoints;
    }

    public double getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(double availablePoints) {
        this.availablePoints = availablePoints;
    }

    public String getCitiCardID() {
        return citiCardID;
    }

    public void setCitiCardID(String citiCardID) {
        this.citiCardID = citiCardID;
    }

    public String getRewardLinkCode() {
        return rewardLinkCode;
    }

    public void setRewardLinkCode(String rewardLinkCode) {
        this.rewardLinkCode = rewardLinkCode;
    }

}
