package citi.vo;

public class User {
    private String userID;
    private String phoneNum;
    private int generalPoints;
    private int availablePoints;
    private String citiCardNum;

    public User(String phoneNum) {
        this.phoneNum = phoneNum;
        this.availablePoints=0;
        this.generalPoints=0;
        //设定唯一标识方式
        this.userID="";
    }

    public User(String userID,String phoneNum, int generalPoints, int availablePoints, String citiCardNum) {
        this.userID = userID;
        this.phoneNum = phoneNum;
        this.generalPoints = generalPoints;
        this.availablePoints = availablePoints;
        this.citiCardNum = citiCardNum;
    }

    /*
     * 作者:曹子轩
     * TODO：具体token相关的逻辑需要实现
     */

    public static User getUserByToken(String token){
        return new User("token");
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

    public String getCitiCardNum() {
        return citiCardNum;
    }

    public void setCitiCardNum(String citiCardNum) {
        this.citiCardNum = citiCardNum;
    }
}
