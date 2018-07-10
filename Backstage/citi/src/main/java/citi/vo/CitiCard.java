package citi.vo;

public class CitiCard {
    private String citiCardID;
    private String citiCardNum;
    private String phoneNum;
    private String userID;

    public CitiCard(String citiCardID, String citiCardNum, String phoneNum, String userID) {
        this.citiCardID = citiCardID;
        this.citiCardNum = citiCardNum;
        this.phoneNum = phoneNum;
        this.userID = userID;
    }

    public String getCitiCardID() {
        return citiCardID;
    }

    public String getCitiCardNum() {
        return citiCardNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getUserID() {
        return userID;
    }
}
