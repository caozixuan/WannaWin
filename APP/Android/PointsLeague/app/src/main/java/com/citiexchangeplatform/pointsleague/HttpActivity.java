package com.citiexchangeplatform.pointsleague;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
    }


    public void login(View view){
        sendRequestWithHttpURLConnection();
    }

    private void sendRequestWithHttpURLConnection(){
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    //获取验证码
                    //URL url = new URL("http://193.112.44.141:8080/citi/login/getVCode");
                    //验证 验证码

                    //URL url = new URL("http://193.112.44.141:80/citi/login/login");
                    URL url = new URL("http://193.112.44.141:80/citi/mscard/infos");
                    //URL url = new URL("http://193.112.44.141:80/citi/merchant/getInfos");

                    //URL url = new URL("http://193.112.44.141:80/citi/mscard/cardtype");
                    connection = (HttpURLConnection) url.openConnection();
                    /*GET方法*/
                    //connection.setRequestMethod("GET");

                    connection.setRequestMethod("POST");
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    out.writeBytes("userId=1ac9be07-f446-458c-b325-df2c7ecd113f&n=1");
                    //out.writeBytes("start=0&n=2");
                    //out.writeBytes("merchantID=00001");
                    //out.writeBytes("phoneNum=12345678901&password=123456");
                    //out.writeBytes("start=0&n=2");
                    //out.writeBytes("phoneNum=17622833370&vcode=996428&password=987654");

                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    //下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }

                    System.out.println(response.toString());
                    /*json字符串最外层是方括号时：*/
                    //JSONArray jsonArray = new JSONArray(response.toString());
                    //for (int i = 0; i < jsonArray.length(); i++) {
                    //    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    //    String mid= jsonObject.getString("MType");
                    //    System.out.println(mid);
                    //}
                    /*json字符串最外层是大括号时：*/
                    //JSONObject jsonObject = new JSONObject(response.toString());
                    //String mid= jsonObject.getString("userID");
                    //String mcourse=jsonObject.getString("phoneNum");
                    //int generalPoint = jsonObject.getInt("generalPoints");
                    //int availablePoints = jsonObject.getInt("availablePoints");
                    //System.out.println(mid);

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (reader != null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行UI操作，将结果显示到界面上
                //responseText.setText(response);
            }
        });
    }
}
