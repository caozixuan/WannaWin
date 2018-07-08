package citi.API;
import okhttp3.*;

import java.io.IOException;
public class Authorize {
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
}
