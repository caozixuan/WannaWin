package citi.API;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Authorize {

    public static String getResponseBody(Request request){
        OkHttpClient client = new OkHttpClient();
        String returnInformation = null;
        try{
            Response response = client.newCall(request).execute();
            ResponseBody RB = response.body();
            returnInformation = RB.string();
        }catch (IOException e)
        {
            System.out.println("error");
        }
        return returnInformation;
    }


    public static String getAccessToken(){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&scope=/api");
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/clientCredentials/oauth2/token/au/gcb")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("authorization", "Basic NTU0NjUwMjYtMzFkMy00ODgxLWEyOWEtNDE5YzM2NGI2N2RiOlc2dlkwbEg4ckw0c0s2bFg2YlA3YUw2ZUMyd1gxbkczYUc3c1I4dkE4bEEzbVMwZUg2")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();
        String return_str="";
        try{
            Response response = client.newCall(request).execute();
            ResponseBody RB = response.body();
            System.out.println(RB.string());
            return_str=RB.string();
        }
        catch (IOException e){
            System.out.println("request error");
        }
        return return_str;
    }

    public static String getURL(String scope, String countryCode, String businessCode, String locale, String state, String redirect_url){
        String client_id = "55465026-31d3-4881-a29a-419c364b67db";
        return "https://sandbox.apihub.citi.com/gcb/api/authCode/oauth2/authorize?response_type=code"+"&client_id="+client_id+"&scope="+scope+"&countryCode="+countryCode+"&businessCode="+businessCode+"&locale="+locale+"&state="+state+"&redirect_uri="+redirect_url;
    }

    public static String getCode(String url){
        Pattern pattern = Pattern.compile("(code=)([^<]*)(&)");
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
			return matcher.group(2);
        }
        return null;
    }
    /*
     返回数据示例
     {
    "token_type": "bearer",
    "access_token": "AAIkNTU0NjUwMjYtMzFkMy00ODgxLWEyOWEtNDE5YzM2NGI2N2RikSTJtNSAMSYdNCHW-Y_tTYBdj_dG-fS0YiREol4x3R_O_E2pRh_mR5S7rqkHiNLhh4CjuomNtYMadgFfj792lkjAAfteewhXWoPc1teL7CKitjt-kOu5odgr6ZpVX1D1yKbJ8YeaXD7HTaCJAYuHhTNiePf6x5489Cbh9PLeN4DVVorSbST7d1B1GafRa_p9e0dSxkFvygCndyJpwwHYGcsGs_rRfCycUrrgRETy9aCctJztpLC80DXnsLG6Z8aHEwYwrivfc7hEpC1ifffuRj0BEk7R9wtdNoo2g0cO_0wtUTWgnyKQ2PzxZueNLZMIUnWSs-azRWUwXMmEPJVGWw",
    "expires_in": 1800,
    "consented_on": 1531052495,
    "scope": "accounts_details_transactions",
    "refresh_token": "AAKgOqqpyvO62mCCaPNR7MRm7xC80W-z1w3kHdjRSZN0thhgZR6sjlHaVzS6CUBwUsXCqdcgfl-26Fhxbg69xQkDiqUIYFXzEScCwigrG4epejBa_LFPyX2ttgI2GmHg2nBFy_be9NbZ5d8_RRp4AX9Xr__-e37tlX9f77PH7tiX6mGFSZ9r6lzL9Odrng7q4qXPhvU8Q_0LozTRPj7Ya_Ok-rReA5gGIborkcaF523QqvR_hRII2IO-AK8vVGMaj-Ntdwy0Ku1IVf4pcXufh1h1BiSq7KJw9r-95loZiaHvYEK_KKJaEgy-U5MEyDxP8mnyln03MUwrTa_isySRCqDrztg3a10kb1ji1Ioa-rAieA",
    "refresh_token_expires_in": 2592000
}
     */
    public static String getAccessTokenWithGrantType(String code, String redirect_URL){
        String returnInformation=null;
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=authorization_code&code="+code+"&redirect_uri="+redirect_URL);
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/authCode/oauth2/token/au/gcb")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("authorization", "Basic NTU0NjUwMjYtMzFkMy00ODgxLWEyOWEtNDE5YzM2NGI2N2RiOlc2dlkwbEg4ckw0c0s2bFg2YlA3YUw2ZUMyd1gxbkczYUc3c1I4dkE4bEEzbVMwZUg2")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();
        returnInformation = Authorize.getResponseBody(request);
        return returnInformation;
    }

    public static String getToken(String tokenInformation){
        JsonElement je = new JsonParser().parse(tokenInformation);
        String access_token=je.getAsJsonObject().get("access_token").toString();
        return access_token;
    }

    public static String getRefreshToken(String tokenInformation){
        JsonElement je = new JsonParser().parse(tokenInformation);
        String refresh_access_token=je.getAsJsonObject().get("refresh_access_token").toString();
        return refresh_access_token;
    }

    public static String refreshToken(){
        String formerRefreshToken = "test"; //这个应该从数据库获取
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=refresh_token&refresh_token="+formerRefreshToken);
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/authCode/oauth2/refresh")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("authorization", "Bearer NTU0NjUwMjYtMzFkMy00ODgxLWEyOWEtNDE5YzM2NGI2N2RiOlc2dlkwbEg4ckw0c0s2bFg2YlA3YUw2ZUMyd1gxbkczYUc3c1I4dkE4bEEzbVMwZUg2")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        String status = Authorize.getResponseBody(request);
        return status;
    }

    /*
      revokeToken:在解绑的时候需要
     */

    public static String revokeToken(String token, String tokenType){
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "token="+token+"&token_type_hint="+tokenType);
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/authCode/oauth2/revoke")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("authorization", "Bearer NTU0NjUwMjYtMzFkMy00ODgxLWEyOWEtNDE5YzM2NGI2N2RiOlc2dlkwbEg4ckw0c0s2bFg2YlA3YUw2ZUMyd1gxbkczYUc3c1I4dkE4bEEzbVMwZUg2\n")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        String status = Authorize.getResponseBody(request);

        return status;
    }
}
