package citi.vo;

public class MSCardType {
    String MerchantID;
    String MType;
    String CardType;
    Double Proportion;

    public MSCardType(String merchantID, String MType, String cardType, Double proportion) {
        MerchantID = merchantID;
        this.MType = MType;
        CardType = cardType;
        Proportion = proportion;
    }

    public String getMerchantID() {
        return MerchantID;
    }

    public void setMerchantID(String merchantID) {
        MerchantID = merchantID;
    }

    public String getMType() {
        return MType;
    }

    public void setMType(String MType) {
        this.MType = MType;
    }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public Double getProportion() {
        return Proportion;
    }

    public void setProportion(Double proportion) {
        Proportion = proportion;
    }
}
