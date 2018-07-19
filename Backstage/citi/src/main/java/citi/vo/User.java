package citi.vo;

import com.google.gson.annotations.Expose;

import java.util.UUID;

/**
 * 重构：刘钟博
 */
public class User {
    @Expose
    private String userID;
    @Expose
    private String citiCardID;
    @Expose(serialize = false)
    private String password;
    @Expose
    private String phoneNum;
    @Expose
    private Double generalPoints;
    @Expose
    private Double availablePoints;
    @Expose
    private String rewardLinkCode;


    public User(String phoneNum, String password) {
        userID = UUID.randomUUID().toString().toLowerCase();
        citiCardID = "";
        this.password = password;
        this.phoneNum = phoneNum;
        generalPoints = 0.0;
        availablePoints = 0.0;
        rewardLinkCode = "";
    }

    public User(String userID, String citiCardID, String password, String phoneNum, Double generalPoints, Double availablePoints, String rewardLinkCode) {
        this.userID = userID;
        this.citiCardID = citiCardID;
        this.password = password;
        this.phoneNum = phoneNum;
        this.generalPoints = generalPoints;
        this.availablePoints = availablePoints;
        this.rewardLinkCode = rewardLinkCode;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Double getGeneralPoints() {
        return generalPoints;
    }

    public void setGeneralPoints(Double generalPoints) {
        this.generalPoints = generalPoints;
    }

    public Double getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(Double availablePoints) {
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
