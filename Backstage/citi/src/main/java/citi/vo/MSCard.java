package citi.vo;

/*
 * 作者：曹子轩
 * TODO: 里面的很多地方需要再沟通
 */
public class MSCard {
    private String userID;
    private String cardNum;
    private int points;
    private String merchantId;
    private double proportion;
    private String logoURL;
    private String merchantName;

    public MSCard(String userID, String cardNum, int points, String merchantId, double proportion, String logoURL, String merchantName) {
        this.userID = userID;
        this.cardNum = cardNum;
        this.points = points;
        this.merchantId = merchantId;
        this.proportion = proportion;
        this.logoURL = logoURL;
        this.merchantName = merchantName;
    }

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

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) {
        this.proportion = proportion;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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
