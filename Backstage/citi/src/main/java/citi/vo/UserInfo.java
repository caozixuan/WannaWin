package citi.vo;

public class UserInfo {
    private String userID;
    private String password;
    private String phoneNum;
    private int generalPoints;
    private int availablePoints;
    private String citiCardNum;

    public UserInfo(String userID, String password, String phoneNum, int generalPoints, int availablePoints, String citiCardNum) {
        this.userID = userID;
        this.password = password;
        this.phoneNum = phoneNum;
        this.generalPoints = generalPoints;
        this.availablePoints = availablePoints;
        this.citiCardNum = citiCardNum;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public int getGeneralPoints() {
        return generalPoints;
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    public String getCitiCardNum() {
        return citiCardNum;
    }
}
