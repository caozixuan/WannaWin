package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class AddCardActivity extends AppCompatActivity {

    ProgressDialog dialog;
    AddCardAdapter addCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_add_card);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置Adapter
        addCardAdapter = new AddCardAdapter(AddCardActivity.this);
        recyclerView.setAdapter(addCardAdapter);
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //new Thread(new getInfos()).start();
        //getInfos();
        addCardAdapter.addData("Apple中国", "苹果中国会员积分卡","http://www.never-give-it-up.top/wp-content/uploads/2018/07/apple_logo.png");
        addCardAdapter.addData("中国移动","中国移动账户积分","http://www.never-give-it-up.top/wp-content/uploads/2018/07/yidong_logo.png");
        addCardAdapter.addData("Nike","Nike旗舰店购物积分","http://www.never-give-it-up.top/wp-content/uploads/2018/07/nike_logo.png");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add_card);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("商家卡列表");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void getInfos() {
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/getInfos")
                .addParam("start", "0")
                .addParam("n", "40")
                .build()
                .execute(AddCardActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jobj = jsonArray.getJSONObject(i);
                                //jobj.getString("merchantID");
                                //jobj.getString("address")
                                addCardAdapter.addData(jobj.getString("name"),jobj.getString("description"),jobj.getString("logoURL"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        dialog.dismiss();
                        Toast.makeText(AddCardActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBefore() {
                        super.onBefore();
                        dialog = ProgressDialog.show(AddCardActivity.this, "", "正在获取商家列表...");
                    }
                });
    }

//    class getInfos implements Runnable {
//
//        @Override
//        public void run() {
//            boolean getInfoSuccess = false;
//
//            HttpURLConnection connection = null;
//            BufferedReader reader = null;
//            try {
//                URL url = new URL("http://193.112.44.141:80/citi/getInfos");
//                connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("POST");
//                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//                out.writeBytes("start=0&n=50");
//                connection.setConnectTimeout(5000);
//                connection.setReadTimeout(5000);
//
//                InputStream in = connection.getInputStream();
//                reader = new BufferedReader(new InputStreamReader(in));
//                String json = reader.readLine();
//                System.out.println(json);
//
//                JSONArray jsonArray = new JSONArray(json);
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject jobj = jsonArray.getJSONObject(i);
//                    //jobj.getString("merchantID");
//                    //jobj.getString("address")
//                    addCardAdapter.addData(jobj.getString("name"),jobj.getString("description"),jobj.getString("logoURL"));
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (reader != null) {
//                    try {
//                        reader.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (connection != null) {
//                    connection.disconnect();
//                }
//            }
//            dialog.dismiss();
//        }
//    }
}
