package citi.dao;

public class UserDAO {
    private String userID;
    private String password;
    private String citiCardID;
    private String phoneNum;
    private int generalPoints;
    private int availablePoints;
    private String rewardLinkCode;

    public UserDAO(String userID, String password, String citiCardID, String phoneNum, int generalPoints, int availablePoints) {
        this.userID = userID;
        this.password = password;
        this.citiCardID = citiCardID;
        this.phoneNum = phoneNum;
        this.generalPoints = generalPoints;
        this.availablePoints = availablePoints;
        this.rewardLinkCode = "";
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCitiCardID() {
        return citiCardID;
    }

    public void setCitiCardID(String citiCardID) {
        this.citiCardID = citiCardID;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getGeneralPoints() {
        return generalPoints;
    }

    public void setGeneralPoints(int generalPoints) {
        this.generalPoints = generalPoints;
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(int availablePoints) {
        this.availablePoints = availablePoints;
    }

    public String getRewardLinkCode() {
        return rewardLinkCode;
    }

    public void setRewardLinkCode(String rewardLinkCode) {
        this.rewardLinkCode = rewardLinkCode;
    }
}
