package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
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

    List<String> names = new ArrayList<String>();
    List<String> descriptions = new ArrayList<String>();
    List<String> logos = new ArrayList<String>();

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
        addCardAdapter = new AddCardAdapter(names, descriptions, logos);
        recyclerView.setAdapter(addCardAdapter);
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        dialog = ProgressDialog.show(AddCardActivity.this, "", "正在获取商家列表...");
        new Thread(new getInfos()).start();
    }


    class getInfos implements Runnable {

        @Override
        public void run() {
            boolean getInfoSuccess = false;

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL("http://193.112.44.141:80/citi/merchant/getInfos");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes("start=0&n=100");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                InputStream in = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in));
                String json = reader.readLine();
                System.out.println(json);

                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jobj = jsonArray.getJSONObject(i);
                    //jobj.getString("merchantID");
                    //jobj.getString("address")
                    addCardAdapter.addData(jobj.getString("name"),jobj.getString("description"),jobj.getString("logoURL"));
                }
                dialog.dismiss();


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }

        }
    }
}
