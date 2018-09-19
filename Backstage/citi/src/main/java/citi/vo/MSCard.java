package citi.vo;

/*
 * 作者：曹子轩
 */
public class MSCard implements Comparable<MSCard>{
    private String userID;
    private String cardNum;
    private Integer points;
    private String merchantID;
    private Double proportion;
    private String logoURL;
    private String merchantName;

    public MSCard(String userID, String cardNum, Integer points, String merchantId, Double proportion, String logoURL, String merchantName) {
        this.userID = userID;
        this.cardNum = cardNum;
        this.points = points;
        this.merchantID = merchantId;
        this.proportion = proportion;
        this.logoURL = logoURL;
        this.merchantName = merchantName;
    }

    public MSCard() {

    }

    public MSCard(String userID, String cardNum, Integer points, String merchantId) {
        this.userID = userID;
        this.cardNum = cardNum;
        this.points = points;
        this.merchantID = merchantId;
    }

    public static boolean checkAttribute(MSCard msCard) {
        //TODO:完整性验证
        return true;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
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
        return merchantID;
    }

    public void setMerchantId(String merchantId) {
        this.merchantID = merchantId;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getUserID() {
        return userID;
    }

    public Integer getPoints() {
        return points;
    }

    @Override
    public int compareTo(MSCard o) {
        if(o.points*o.proportion-this.points*this.proportion>0)
            return 1;
        return -1;
    }

}
