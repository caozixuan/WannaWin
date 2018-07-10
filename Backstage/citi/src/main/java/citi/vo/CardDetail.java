package citi.vo;

import java.util.ArrayList;

/*
        TODO:这个json解析待测试
        {
  "cardDetails": [
    {
      "cardId": "3255613852316f2b4d4d796c344e38756339654972776f663745446e6d4c32486f455a4165374a476858343d",
      "displayCardNumber": "XXXXXXXXXXXX4521",
      "localCardActivationIndicator": "ACTIVE",
      "overseasCardActivationIndicator": "ACTIVE",
      "perpetualActivationFlag": true,
      "overseasCardActivationStartDate": "2016-11-01",
      "overseasCardActivationEndDate": "2016-12-05",
      "currentCreditLimitAmount": 3500.25,
      "maximumPermanentCreditLimitAmount": 5000.25,
      "maximumTemporaryCreditLimitAmount": 5000.25,
      "subCardType": "DEBIT",
      "cardHolderType": "PRIMARY",
      "cardIssueReason": "NEWLY_ONBOARDED_CARD",
      "cardFunctionsAllowed": [
        {
          "cardFunction": "CREDIT_LIMIT_INCREASE"
        }
      ]
    }
  ]
}
         */
public class CardDetail {
    String cardId;
    String displayCardNumber;
    String localCardActivationIndicator;
    String overseasCardActivationIndicator;
    boolean perpetualActivationFlag;
    String overseasCardActivationStartDate;
    String overseasCardActivationEndDate;
    Double currentCreditLimitAmount;
    Double maximumPermanentCreditLimitAmount;
    Double maximumTemporaryCreditLimitAmount;
    String subCardType;
    String cardHolderType;
    String cardIssueReason;
    ArrayList<cardFunctionsAllowed>  cardFunction;
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
