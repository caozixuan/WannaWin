package citi.API;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.UUID;

import static citi.API.CitiAuthorize.step3GetRealAccessToken;

public class CitiAccount {

    public static String getAccounts(CitiAPIContext context) throws IOException {
        String client_id = CitiAPIConstant.CLIENT_ID;
        String authorization = "Bearer " + context.getRealAccessToken();
        UUID uuid = UUID.randomUUID();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/accounts")
                .get()
                .addHeader("authorization", authorization)
                .addHeader("uuid", uuid.toString())
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                .addHeader("client_id", client_id)
                .build();
        Response response = client.newCall(request).execute();
        String responseBodyString = response.body().string();
        context.setAccounts(responseBodyString);
        return responseBodyString;
    }

    public static String getAccounts(String username, String password, CitiAPIContext context) throws IOException {
        context.setUsername(username);
        context.setPassword(password);
        step3GetRealAccessToken(context);
        if(context.getRealAccessToken()==null){
            return null;
        }
        String accounts = getAccounts(context);
        if(context.getAccounts()==null){
            return null;
        }
        return accounts;
    }
}
