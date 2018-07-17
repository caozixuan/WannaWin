/**********      Deprecated      **********
 * 2018-07-11 16:07
 * 每个商户只允许发行一种卡，
 * 消费策略和商户绑定。

 package citi.vo;

import java.io.IOException;
import java.io.StringReader;

import com.google.GSON.*;
//import org.apache.commons.*;
import com.google.GSON.stream.JsonReader;
import com.google.GSON.stream.JsonToken;

public class MSCardType {
    private String MerchantID;
    private String MType;
    private String CardTypeID;
    private Double Proportion;
    private String miniExpense;



    public MSCardType(String cardTypeID) {
        CardTypeID = cardTypeID;
    }

    //
    public String toString() {
        GsonBuilder builder = new GsonBuilder();
        Gson GSON = builder.create();
        //System.out.println(GSON.toJson(this));
        return GSON.toJson(this);
    }

    public MSCardType(String merchantID, String MType, String cardTypeID, Double proportion, String miniExpense) {
        MerchantID = merchantID;
        this.MType = MType;
        CardTypeID = cardTypeID;
        Proportion = proportion;
        this.miniExpense = miniExpense;
    }

    public String getMerchantID() {
        return MerchantID;
    }

    public String getMType() {
        return MType;
    }

    public String getCardTypeID() {
        return CardTypeID;
    }

    public Double getProportion() {
        return Proportion;
    }

    public String getMiniExpense() {
        return miniExpense;
    }

}
*/