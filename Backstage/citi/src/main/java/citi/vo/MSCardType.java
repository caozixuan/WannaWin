package citi.vo;

public class MSCardType {
    private String MerchantID;
    private String MType;
    private String CardType;
    private Double Proportion;
    private String miniExpense;

    //Type of CardType is String in consistence with DB
    public MSCardType(String CardType) {
        this.MerchantID = "";
        this.MType = "";
        this.CardType = "";
        this.Proportion = 0.0;
        this.miniExpense = "";
    }

    public MSCardType(String merchantID, String MType, String cardType, Double proportion, String miniExpense) {
        MerchantID = merchantID;
        this.MType = MType;
        CardType = cardType;
        Proportion = proportion;
        this.miniExpense = miniExpense;
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

    public String getMiniExpense() {
        return miniExpense;
    }

    public void setMiniExpense(String miniExpense) {
        this.miniExpense = miniExpense;
    }
}
