package citi.vo;

public class CitiCard {
    private String citiCardNum;
    private String phoneNum;
    private String ID;
    private String password;

    public CitiCard(String citiCardNum, String phoneNum, String ID, String password) {
        this.citiCardNum = citiCardNum;
        this.phoneNum = phoneNum;
        this.ID = ID;
        this.password = password;
    }

    public String getCitiCardNum() {
        return citiCardNum;
    }

    public void setCitiCardNum(String citiCardNum) {
        this.citiCardNum = citiCardNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
