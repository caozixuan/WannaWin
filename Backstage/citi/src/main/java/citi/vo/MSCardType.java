package citi.vo;

import java.io.IOException;
import java.io.StringReader;

import com.google.gson.*;
//import org.apache.commons.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class MSCardType {
    private String MerchantID;
    private String MType;
    private String CardTypeID;
    private Double Proportion;
    private String miniExpense;

    //Type of CardType is String in consistence with DB
/*    public MSCardType(String CardTypeJson) throws IOException {
        JsonReader reader = new JsonReader(new StringReader(CardTypeJson));
        reader.beginObject();
        MSCardType temp = new GsonBuilder().create().fromJson(CardTypeJson, MSCardType.class);
        this.MerchantID = temp.MerchantID;
        this.MType = temp.MType;
        this.CardType = temp.CardType;
        this.Proportion = temp.Proportion;
        this.miniExpense = temp.miniExpense;
    }*/

    public MSCardType(String cardTypeID) {
        CardTypeID = cardTypeID;
    }

    //
    public String toString() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        //System.out.println(gson.toJson(this));
        return gson.toJson(this);
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
