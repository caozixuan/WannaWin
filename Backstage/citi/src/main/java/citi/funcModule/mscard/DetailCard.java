package citi.funcModule.mscard;

public class DetailCard implements  Comparable<DetailCard>{
    private String cardLogoURL;
    private int points;
    private String cardNum;
    private String cardDescription;
    private int type=0;
    private double proportion;
    private String merchantName;
    private String merchantLogoURL;

    public DetailCard(String cardLogoURL, int points, String cardNum, String cardDescription, int type, double proportion, String merchantName, String merchantLogoURL) {
        this.cardLogoURL = cardLogoURL;
        this.points = points;
        this.cardNum = cardNum;
        this.cardDescription = cardDescription;
        this.type = type;
        this.proportion = proportion;
        this.merchantName = merchantName;
        this.merchantLogoURL = merchantLogoURL;
    }

    public String getCardLogoURL() {
        return cardLogoURL;
    }

    public int getPoints() {
        return points;
    }

    public String getCardNum() {
        return cardNum;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public int getType() {
        return type;
    }

    @Override
    public int compareTo(DetailCard o) {
        if(o.points*o.proportion-this.points*this.proportion>0)
            return 1;
        return -1;
    }
}
