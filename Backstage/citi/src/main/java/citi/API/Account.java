package citi.API;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class Account {
    public static String getAccountInformation(String url, String redirect_URL){
        String returnInformation = Authorize.getAccessTokenWithGrantType(url,redirect_URL);
        JsonElement je = new JsonParser().parse(returnInformation);
        String access_token=je.getAsJsonObject().get("access_token").toString();
        OkHttpClient client = new OkHttpClient();
        String information = null;
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/accounts?nextStartIndex=1")
                .get()
                .addHeader("authorization", "Bearer "+access_token)
                .addHeader("uuid", "271e2744-3c60-442e-aa0f-874e1551e57a")
                .addHeader("accept", "application/json")
                .addHeader("client_id", "55465026-31d3-4881-a29a-419c364b67db")
                .build();

        try{
            Response response = client.newCall(request).execute();
            ResponseBody RB = response.body();
            information = RB.string();
        }catch (IOException e){
            System.out.println("error");
        }
        return information;
    }
}
