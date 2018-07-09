package citi.API;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.UUID;

public class Account {
    public static String getAccountInformation(String access_token){
        OkHttpClient client = new OkHttpClient();
        String information = null;
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/accounts?nextStartIndex=1")
                .get()
                .addHeader("authorization", "Bearer "+access_token)
                .addHeader("uuid", UUID.randomUUID().toString())
                .addHeader("accept", "application/json")
                .addHeader("client_id", "55465026-31d3-4881-a29a-419c364b67db")
                .build();

        information = PayWithAwards.getResponseBody(request);
        return information;
    }
}
