package citi.vo;

import java.util.ArrayList;


public class CardDetail {
    private String cardId;
    private String displayCardNumber;
    private String localCardActivationIndicator;
    private String overseasCardActivationIndicator;
    private boolean perpetualActivationFlag;
    private String overseasCardActivationStartDate;
    private String overseasCardActivationEndDate;
    private Double currentCreditLimitAmount;
    private Double maximumPermanentCreditLimitAmount;
    private Double maximumTemporaryCreditLimitAmount;
    private String subCardType;
    private String cardHolderType;
    private String cardIssueReason;
    private ArrayList<cardFunctionsAllowed>  cardFunction;
    class cardFunctionsAllowed{
        String cardFunction;
    }

    public CardDetail(String cardId, String displayCardNumber, String localCardActivationIndicator, String overseasCardActivationIndicator, boolean perpetualActivationFlag, String overseasCardActivationStartDate, String overseasCardActivationEndDate, Double currentCreditLimitAmount, Double maximumPermanentCreditLimitAmount, Double maximumTemporaryCreditLimitAmount, String subCardType, String cardHolderType, String cardIssueReason, ArrayList<cardFunctionsAllowed> cardFunction) {
        this.cardId = cardId;
        this.displayCardNumber = displayCardNumber;
        this.localCardActivationIndicator = localCardActivationIndicator;
        this.overseasCardActivationIndicator = overseasCardActivationIndicator;
        this.perpetualActivationFlag = perpetualActivationFlag;
        this.overseasCardActivationStartDate = overseasCardActivationStartDate;
        this.overseasCardActivationEndDate = overseasCardActivationEndDate;
        this.currentCreditLimitAmount = currentCreditLimitAmount;
        this.maximumPermanentCreditLimitAmount = maximumPermanentCreditLimitAmount;
        this.maximumTemporaryCreditLimitAmount = maximumTemporaryCreditLimitAmount;
        this.subCardType = subCardType;
        this.cardHolderType = cardHolderType;
        this.cardIssueReason = cardIssueReason;
        this.cardFunction = cardFunction;
    }

    public String getCardId() {
        return cardId;
    }

    public String getDisplayCardNumber() {
        return displayCardNumber;
    }
}
