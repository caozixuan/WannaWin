package citi.API;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;
import java.util.UUID;

public class CitiPayWithAwards {
    public static String getLinkCode(CitiAPIContext context, String cardNum, String phoneNum, String merchantCustomerReferenceId){
        String linkCode = null;
        String accessToken = context.getRealAccessToken();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"lastFourDigitsCardNumber\":"+cardNum+",\"citiCardHolderPhoneNumber\":"+phoneNum+",\"merchantCustomerReferenceId\":"+merchantCustomerReferenceId+"}");
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/apac/rewards/linkage")
                .post(body)
                .addHeader("authorization", "Bearer "+accessToken)
                .addHeader("uuid", UUID.randomUUID().toString())
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("accept-language", "en-us")
                .addHeader("client_id", CitiAPIConstant.CLIENT_ID)
                .build();

        linkCode = Authorize.getResponseBody(request);
        JsonElement je = new JsonParser().parse(linkCode);
        linkCode=je.getAsJsonObject().get("rewardLinkCode").toString();
        linkCode = Authorize.getTokenByRF(linkCode);
        return linkCode;
    }

    public static void activateCode(CitiAPIContext context, String linkCode){
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"linkageConfirmationCode\":"+linkCode+"}");
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/apac/rewards/"+linkCode+"/activations")
                .put(body)
                .addHeader("uuid",  UUID.randomUUID().toString())
                .addHeader("accept", "application/json")
                .addHeader("client_id", CitiAPIConstant.CLIENT_ID)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer "+context.getRealAccessToken())
                .addHeader("accept-language", "en-us")
                .build();
        try{
            Response response = client.newCall(request).execute();
        }catch (IOException e){
            System.out.println("error");
        }
    }

    public static String getInformation(CitiAPIContext context, String linkCode){
        String information = null;
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/apac/rewards/"+linkCode+"/pointBalance")
                .get()
                .addHeader("authorization", "Bearer "+context.getRealAccessToken())
                .addHeader("uuid",  UUID.randomUUID().toString())
                .addHeader("accept", "application/json")
                .addHeader("client_id", CitiAPIConstant.CLIENT_ID)
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
                .addHeader("client_id", CitiAPIConstant.CLIENT_ID)
                .addHeader("accept-language", "en-us")
                .build();

        String information = Authorize.getResponseBody(request);
        return information;
    }
}
