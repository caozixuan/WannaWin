package citi.dao;

import citi.vo.MSCard;
import citi.vo.MSCardType;

import java.io.IOException;

public class MSCardDAO {
    String cardID;
    String userID;
    String cardNo;
    int points;
    String CardType;

    public MSCardDAO(String cardID, String userID, String cardNo, int points, String msCardType) {
        this.cardID = cardID;
        this.userID = userID;
        this.cardNo = cardNo;
        this.points = points;
        this.CardType = msCardType;
    }

    public MSCard toMSCard() {
        MSCard msCard = null;
        try{
            msCard = new MSCard(cardID, userID, cardNo, points, new MSCardType(CardType));
        }catch (IOException e){
            System.out.println("error");
        }
        return msCard;
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
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getMsCardType() {
        return CardType;
    }

    public void setMsCardType(String msCardType) {
        this.CardType = msCardType;
    }
}
