package citi.API;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CitiAuthorize {
    public static Map getBizToken(CitiAPIContext context) throws IOException {
        step1GetAccessToken(context);
        if(context.getAccessToken()==null){
            return null;
        }
        Map map = step2GetBizToken(context);
        if(context.getEventId()==null){
            return null;
        }
        return map;
    }

    public static String step1GetAccessToken(CitiAPIContext context) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String client_id = CitiAPIConstant.CLIENT_ID;
        String client_scrent = CitiAPIConstant.CLIENT_SCRENT;
        String encode_key = client_id + ":" + client_scrent;
        String authorization = "Basic " + Base64.encodeBase64String(encode_key.getBytes());
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&scope=/api");
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/clientCredentials/oauth2/token/hk/gcb")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("authorization", authorization)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject jsonObject = (JSONObject) JSONValue.parse(response.body().string());
        String accessToken = (String) jsonObject.get("access_token");
        context.setAccessToken(accessToken);
        System.out.println("step1 access_token:");
        System.out.println("\t" + accessToken);
        return accessToken;
    }

    public static Map step2GetBizToken(CitiAPIContext context) throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        OkHttpClient client = new OkHttpClient();
        String client_id = CitiAPIConstant.CLIENT_ID;
        String accessToken = context.getAccessToken();
        String authorization = "Bearer " + accessToken;
        UUID uuid = UUID.randomUUID();
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/security/e2eKey")
                .get()
                .addHeader("authorization", authorization)
                .addHeader("client_id", client_id)
                .addHeader("uuid", uuid.toString())
                .addHeader("content-type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject jsonObject = (JSONObject) JSONValue.parse(response.body().string());
        String modulus = null;
        String exponent = null;
        String bizToken = null;
        String eventId = null;
        if (jsonObject != null) {
            modulus = (String) jsonObject.get("modulus");
            exponent = (String) jsonObject.get("exponent");
            Headers headers = response.headers();
            bizToken = headers.get("bizToken");
            eventId = headers.get("eventId");
            map.put("modulus", modulus);
            map.put("exponent", exponent);
            map.put("bizToken", bizToken);
            map.put("eventId", eventId);
            context.setEventId(eventId);
            context.setBizToken(bizToken);
        }
        System.out.println("step2 map:");
        for (String s : map.keySet()) {
            System.out.println("\tkey:" + s + "\tvalues:" + map.get(s));
        }
        return map;
    }

    public static String step3GetRealAccessToken(CitiAPIContext context) throws IOException{
        String client_id = CitiAPIConstant.CLIENT_ID;
        String client_scrent = CitiAPIConstant.CLIENT_SCRENT;
        String bizToken = context.getBizToken();
        System.err.println("bizToken: "+bizToken);
        String encode_key = client_id + ":" + client_scrent;
        String authorization = "Basic " + Base64.encodeBase64String(encode_key.getBytes());
        String username = context.getUsername();
        String password = context.getPassword();
        System.out.println(password);
        UUID uuid = UUID.randomUUID();
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=password&scope=/api&username="+username+"&password="+password);
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/password/oauth2/token/hk/gcb")
                .post(body)
                .addHeader("authorization", authorization)
                .addHeader("bizToken", bizToken)
                .addHeader("uuid", uuid.toString())
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject jsonObject = (JSONObject) JSONValue.parse(response.body().string());
        String realAccessToken = (String) jsonObject.get("access_token");
        context.setRealAccessToken(realAccessToken);
        System.out.println("step3 real_access_token:");
        System.out.println("\t" + realAccessToken);
        return realAccessToken;
    }
}
