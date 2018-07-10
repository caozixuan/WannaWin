package citi.dao;

import citi.vo.MSCard;
import citi.vo.MSCardType;

import java.io.IOException;
import java.util.UUID;

public class MSCardDAO {
    private String cardID;
    private String userID;
    private String card_No;
    private int points;
    private String CardType;

    public MSCardDAO(String userID, String cardNo, int points, String msCardType) {
        this.cardID = UUID.randomUUID().toString().toLowerCase();
        this.userID = userID;
        this.card_No = cardNo;
        this.points = points;
        this.CardType = msCardType;
    }

    public MSCardDAO() {
        this.cardID= UUID.randomUUID().toString().toLowerCase();
    }

    public MSCard toMSCard() {
        MSCard msCard = null;

        msCard = new MSCard(cardID, userID, card_No, points, new MSCardType(CardType));
        /*catch (IOException e) {
            System.out.println("error");
        }*/
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

    public String getMsCardType() {
        return CardType;
    }

    public void setMsCardType(String msCardType) {
        this.CardType = msCardType;
    }
}
