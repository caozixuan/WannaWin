package citi.vo;

import java.util.UUID;

public class User {
    private String userID;
    private String citiCard;
    private String phoneNum;
    private int generalPoints;
    private int availablePoints;

    public User(String phoneNum) {
        this.phoneNum = phoneNum;
        this.availablePoints = 0;
        this.generalPoints = 0;
        //设定唯一标识方式
        this.userID = "";
    }

    public User(String citiCardNum, String phoneNum, int generalPoints, int availablePoints) {
        this.userID = UUID.randomUUID().toString().toLowerCase();
        this.citiCard = citiCardNum;
        this.phoneNum = phoneNum;
        this.generalPoints = generalPoints;
        this.availablePoints = availablePoints;
    }

    public User(String userID, String citiCardNum, String phoneNum, int generalPoints, int availablePoints) {
        this.userID = userID;
        this.citiCard = citiCardNum;
        this.phoneNum = phoneNum;
        this.generalPoints = generalPoints;
        this.availablePoints = availablePoints;
    }

    /*
     * 作者:曹子轩
     * TODO：具体token相关的逻辑需要实现
     */

    public static User getUserByToken(String token) {
        return new User("token");
    }

    public void changePoint(Order order) {
        //TODO:这里需要根据用户的设定扣除积分，扣除过程应该会比较复杂
        if (order.getPointsNeeded() > 0)
            this.setAvailablePoints(this.getAvailablePoints() - order.getPointsNeeded());
        order.changeState();
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
        return citiCard;
    }

    public void setCitiCardNum(String citiCardNum) {
        this.citiCard = citiCardNum;
    }
}
