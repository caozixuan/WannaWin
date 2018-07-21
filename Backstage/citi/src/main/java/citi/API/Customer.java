package citi.API;

import okhttp3.OkHttpClient;
import okhttp3.Request;


import java.util.UUID;

public class Customer {
    public static String getCustomerPhone(String accessToken){
        String client_id = "55465026-31d3-4881-a29a-419c364b67db";
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/customers/profiles/phoneNumbers")
                .get()
                .addHeader("authorization", "Bearer "+accessToken)
                .addHeader("uuid", UUID.randomUUID().toString())
                .addHeader("accept", "application/json")
                .addHeader("client_id", client_id)
                .build();

        String phoneInformation = Authorize.getResponseBody(request);
        return phoneInformation;
    }
}
