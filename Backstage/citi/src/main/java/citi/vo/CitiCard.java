package citi.vo;

public class CitiCard {
    private String citiCardNum;
    private String phoneNum;
    private String ID;
    private String userID;
    private String password;

    public CitiCard(String citiCardNum, String phoneNum, String ID, String userID, String password) {
        this.citiCardNum = citiCardNum;
        this.phoneNum = phoneNum;
        this.ID = ID;
        this.userID = userID;
        this.password = password;
    }

    public CitiCard(String citiCardNum, String phoneNum, String userID) {
        this.citiCardNum = citiCardNum;
        this.phoneNum = phoneNum;
        this.userID = userID;
    }

    public String getCitiCardNum() {
        return citiCardNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getID() {
        return ID;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }
}
