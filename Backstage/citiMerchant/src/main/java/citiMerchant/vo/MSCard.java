package citiMerchant.vo;

public class MSCard {
    private String userID;
    private String cardNum;
    private int points;
    private String merchantId;

    public MSCard() {
    }

    public MSCard(String userID, String cardNum, int points, String merchantId) {
        this.userID = userID;
        this.cardNum = cardNum;
        this.points = points;
        this.merchantId = merchantId;
    }

    public static boolean checkAttribute(MSCard msCard) {
        //TODO:完整性验证
        return true;
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

    public String getUserID() {
        return userID;
    }

    public int getPoints() {
        return points;
    }

}
