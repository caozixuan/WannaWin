package citi.mscard;

public class DetailCard {
    private String cardLogoURL;
    private int points;
    private String cardNum;
    private String cardDescription;
    private int type=0;
    private double proportion;

    public DetailCard(String cardLogoURL, int points, String cardNum, String cardDescription, int type, double proportion) {
        this.cardLogoURL = cardLogoURL;
        this.points = points;
        this.cardNum = cardNum;
        this.cardDescription = cardDescription;
        this.type = type;
        this.proportion = proportion;
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
}
