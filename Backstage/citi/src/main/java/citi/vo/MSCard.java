package citi.vo;

/*
 * 作者：曹子轩
 * TODO: 里面的很多地方需要再沟通
 */
public class MSCard {
    String cardID;
    String UserID;
    String merchantID;
    String cardNo;
    int points;
    double proportion;
    MSCardType msCardType;

    public String getCardID() {
        return cardID;
    }

    public String getUserID() {
        return UserID;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public String getCardNo() {
        return cardNo;
    }

    public int getPoints() {
        return points;
    }

    public double getProportion() {
        return proportion;
    }
}
