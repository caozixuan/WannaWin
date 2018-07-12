package citiMerchant.vo;

import java.util.UUID;

/*
 * 作者：曹子轩
 * TODO: 里面的很多地方需要再沟通
 */
public class MSCard {
    private String cardID;
    private String userID;
    private String cardNum;
    private int points;
    private String merchantId;

    public MSCard() {
        cardID = UUID.randomUUID().toString().toLowerCase();
        //TODO:从商家获取points或其他途径
    }

    public MSCard(String cardID, String userID, String cardNum, int points, String merchantId) {
        this.cardID = cardID;
        this.userID = userID;
        this.cardNum = cardNum;
        this.points = points;
        this.merchantId = merchantId;
    }

    public static boolean checkAttribute(MSCard msCard) {
        //TODO:完整性验证
        return true;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getCardID() {
        return cardID;
    }

    public String getUserID() {
        return userID;
    }

    public int getPoints() {
        return points;
    }

}
