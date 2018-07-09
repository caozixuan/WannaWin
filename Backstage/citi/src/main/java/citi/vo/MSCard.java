package citi.vo;

/*
 * 作者：曹子轩
 * TODO: 里面的很多地方需要再沟通
 */
public class MSCard {
    String cardID;
    String userID;
    String cardNo;
    int points;
    MSCardType CardType;


    public MSCard(String cardID, String userID, String cardNo, int points, MSCardType CardType) {
        this.cardID = cardID;
        this.userID = userID;
        this.cardNo = cardNo;
        this.points = points;
        this.CardType = CardType;
    }

    public MSCard(String cardID, String userID, String cardNo, MSCardType cardType) {
        this.cardID = cardID;
        this.userID = userID;
        this.cardNo = cardNo;
        CardType = cardType;
    }

    public String getCardID() {
        return cardID;
    }

    public String getUserID() {
        return userID;
    }

    public String getCardNo() {
        return cardNo;
    }

    public int getPoints() {
        return points;
    }

}