package citi.API;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;
import java.util.UUID;

public class PayWithAwards {


    public static String getLinkCode(String cardNum, String phoneNum, String merchantCustomerReferenceId, String accessToken){
        String linkCode = null;
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"lastFourDigitsCardNumber\":"+cardNum+",\"citiCardHolderPhoneNumber\":"+phoneNum+",\"merchantCustomerReferenceId\":"+merchantCustomerReferenceId+"}");
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/apac/rewards/linkage")
                .post(body)
                .addHeader("authorization", "Bearer "+accessToken)
                .addHeader("uuid", "b4149df3-b7fb-460f-b79e-29492d55bdd9")
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("accept-language", "en-us")
                .addHeader("client_id", "55465026-31d3-4881-a29a-419c364b67db")
                .build();

        linkCode = Authorize.getResponseBody(request);
        JsonElement je = new JsonParser().parse(linkCode);
        linkCode=je.getAsJsonObject().get("rewardLinkCode").toString();
        linkCode = Authorize.getTokenByRF(linkCode);
        return linkCode;
    }

    public static void activateCode(String linkCode, String accessToken){
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"linkageConfirmationCode\":"+linkCode+"}");
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/apac/rewards/"+linkCode+"/activations")
                .put(body)
                .addHeader("uuid",  UUID.randomUUID().toString())
                .addHeader("accept", "application/json")
                .addHeader("client_id", "55465026-31d3-4881-a29a-419c364b67db")
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer "+accessToken)
                .addHeader("accept-language", "en-us")
                .build();
        try{
            Response response = client.newCall(request).execute();
        }catch (IOException e){
            System.out.println("error");
        }
    }

    public static String getInformation(String linkCode, String accessToken){
        String information = null;
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/apac/rewards/"+linkCode+"/pointBalance")
                .get()
                .addHeader("authorization", "Bearer "+accessToken)
                .addHeader("uuid",  UUID.randomUUID().toString())
                .addHeader("accept", "application/json")
                .addHeader("client_id", "55465026-31d3-4881-a29a-419c364b67db")
                .addHeader("accept-language", "en-us")
                .build();

        information = Authorize.getResponseBody(request);
        return information;
    }

    public static String finishOrder(String linkCode, String orderInformation){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        //RequestBody body = RequestBody.create(mediaType, "{\"transactionReferenceNumber\":\"132323454de6234543\",\"redemptionOrder\":{\"transactionAmount\":100.5,\"currencyCode\":\"AUD\",\"pointsToRedeem\":1005,\"transactionDescription\":\"Completed\"}}");
        RequestBody body = RequestBody.create(mediaType, orderInformation);
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/apac/rewards/"+linkCode+"/redemption")
                .post(body)
                .addHeader("authorization", "Bearer "+linkCode)
                .addHeader("uuid",  UUID.randomUUID().toString())
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("client_id", "55465026-31d3-4881-a29a-419c364b67db")
                .addHeader("accept-language", "en-us")
                .build();

        String information = Authorize.getResponseBody(request);
        return information;
    }


}
