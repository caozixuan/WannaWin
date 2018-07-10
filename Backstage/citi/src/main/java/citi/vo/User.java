package citi.vo;

import citi.dao.OrderDAO;
import citi.mybatismapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class User {
    private String userID;
    private String citiCardID;
    private String phoneNum;
    private int generalPoints;
    private int availablePoints;
    private String rewardLinkCode;


    public User(String citiCardID, String phoneNum, int generalPoints, int availablePoints) {
        this.userID = UUID.randomUUID().toString().toLowerCase();
        this.citiCardID = citiCardID;
        this.phoneNum = phoneNum;
        this.generalPoints = generalPoints;
        this.availablePoints = availablePoints;
        this.rewardLinkCode = "";
    }

    public User(String userID, String citiCardNum, String phoneNum, int generalPoints, int availablePoints) {
        this.userID = userID;
        this.citiCardID = citiCardNum;
        this.phoneNum = phoneNum;
        this.generalPoints = generalPoints;
        this.availablePoints = availablePoints;
        this.rewardLinkCode = "";
    }

    /*
     * 作者:曹子轩
     * TODO：具体token相关的逻辑需要实现
     */
    @Autowired
    static private UserMapper userMapper;

    public static User getUserByToken(String token) {
        String phoneNum = "";//according to token
        return userMapper.getInfoByPhone(phoneNum);
    }

    public void changePoint(OrderDAO orderDAO) {
        //TODO:这里需要根据用户的设定扣除积分，扣除过程应该会比较复杂
        if (orderDAO.getPointsNeeded() > 0)
            this.setAvailablePoints(this.getAvailablePoints() - orderDAO.getPointsNeeded());
        orderDAO.changeState();
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

    public String getCitiCardID() {
        return citiCardID;
    }

    public void setCitiCardID(String citiCardID) {
        this.citiCardID = citiCardID;
    }

    public String getRewardLinkCode() {
        return rewardLinkCode;
    }

    public void setRewardLinkCode(String rewardLinkCode) {
        this.rewardLinkCode = rewardLinkCode;
    }

}
