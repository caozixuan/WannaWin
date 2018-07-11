package citi.dao;

import citi.vo.MSCard;
//import citi.vo.MSCardType;

import java.io.IOException;
import java.util.UUID;

public class MSCardDAO {
    private String cardID;
    private String userID;
    private String card_No;
    private int points;
    private String merchantID;

    public MSCardDAO(String userID, String cardNo, int points, String merchantID) {
        this.cardID = UUID.randomUUID().toString().toLowerCase();
        this.userID = userID;
        this.card_No = cardNo;
        this.points = points;
        this.merchantID = merchantID;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCardNo() {
        return card_No;
    }

    public void setCardNo(String cardNo) {
        this.card_No = cardNo;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String msCardType) {
        this.merchantID = msCardType;
    }

    public static boolean checkAttribute(MSCardDAO msCardDAO) {
        if (msCardDAO.cardID != null && msCardDAO.userID != null && msCardDAO.card_No != null && msCardDAO.merchantID != null) {
            return true;
        }
        return false;
    }
}
