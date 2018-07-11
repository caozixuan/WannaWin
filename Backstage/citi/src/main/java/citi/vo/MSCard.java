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

    public static boolean checkAttribute(MSCard msCard){

        return false;
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
