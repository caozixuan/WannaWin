package citi.vo;

public class CitiCard {
    private String citiCardNum;
    private String phoneNum;
    private String userID;

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

    public String getUserID() {
        return userID;
    }

}
