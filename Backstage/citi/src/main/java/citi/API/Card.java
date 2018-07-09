package citi.API;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.util.UUID;

public class Card {
    public static String getCardsInformation(String accessToken){
        OkHttpClient client = new OkHttpClient();
        String client_id = "55465026-31d3-4881-a29a-419c364b67db";
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/cards?cardFunction=ALL")
                .get()
                .addHeader("authorization", "Bearer "+accessToken)
                .addHeader("client_id", client_id)
                .addHeader("uuid", UUID.randomUUID().toString())
                .addHeader("accept", "application/json")
                .build();

        String cardsInformation = Authorize.getResponseBody(request);
        return cardsInformation;
    }
}
