package citi.funcModule.mscard;

public class CardDescriptionBean {
    private String cardDescription;
    private String cardStyle;

    public CardDescriptionBean(String cardDescription, String cardStyle) {
        this.cardDescription = cardDescription;
        this.cardStyle = cardStyle;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public String getCardStyle() {
        return cardStyle;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public void setCardStyle(String cardStyle) {
        this.cardStyle = cardStyle;
    }
}
