package citi.vo;

import java.text.DecimalFormat;

public class CitiCard {
    private String citiCardID;
    private String citiCardNum;
    private String phoneNum;
    private String userID;
    private Double miniExpense;

    public CitiCard(String citiCardID, String citiCardNum, String phoneNum, String userID, Double miniExpense) {
        this.citiCardID = citiCardID;
        this.citiCardNum = citiCardNum;
        this.phoneNum = phoneNum;
        this.userID = userID;
        DecimalFormat df = new DecimalFormat("#.00");
        this.miniExpense = Double.valueOf(df.format(miniExpense));
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

    public Double getMiniExpense() {
        return miniExpense;
    }

}
