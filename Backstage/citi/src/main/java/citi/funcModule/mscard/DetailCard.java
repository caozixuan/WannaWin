package citi.funcModule.mscard;

import com.google.gson.Gson;

public class DetailCard implements  Comparable<DetailCard>{
    private String cardLogoURL;
    private int points;
    private String cardNum;
    private String cardDescription;
    private int cardStyle;
    private int type=0;
    private double proportion;
    private String merchantName;
    private String merchantLogoURL;

    public DetailCard(String cardLogoURL, int points, String cardNum, String cardDescription, int cardStyle, int type, double proportion, String merchantName, String merchantLogoURL) {
        this.cardLogoURL = cardLogoURL;
        this.points = points;
        this.cardNum = cardNum;
        this.cardDescription = cardDescription;
        this.cardStyle = cardStyle;
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
