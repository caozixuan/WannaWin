package citi.dao;

public class UserDAO {
    private String userID;
    private String password;
    private String citiCard;
    private String phoneNum;
    private int generalPoints;
    private int availablePoints;

    public UserDAO(String userID, String password, String citiCard, String phoneNum, int generalPoints, int availablePoints) {
        this.userID = userID;
        this.password = password;
        this.citiCard = citiCard;
        this.phoneNum = phoneNum;
        this.generalPoints = generalPoints;
        this.availablePoints = availablePoints;
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

    public String getCitiCard() {
        return citiCard;
    }

    public void setCitiCard(String citiCard) {
        this.citiCard = citiCard;
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
}
