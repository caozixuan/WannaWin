package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.citiexchangeplatform.pointsleague.adapter.HistoryExchangeAdapter;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HistoryExchangeActivity extends AppCompatActivity {

    HistoryExchangeAdapter historyExchangeAdapter;
    ProgressDialog dialog;
    String merchantID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_exchange);

        Intent intent = getIntent();
        merchantID = intent.getStringExtra("merchantID");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_history_exchange);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        historyExchangeAdapter = new HistoryExchangeAdapter(HistoryExchangeActivity.this);
        recyclerView.setAdapter(historyExchangeAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        TitleBar titleBar = (TitleBar) findViewById(R.id.toolbar_history_exchange);
        titleBar.setLeftImageResource(R.drawable.ic_left_orange_24dp);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextColor(0xFFFF9546);

        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleBar.setTitle("兑换记录");
        titleBar.setTitleColor(Color.BLACK);

        showHistoryExchange();
    }

    private void showHistoryExchange(){
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/points/getPointsHistoryByMerchantID")
                .addParam("userID", LogStateInfo.getInstance(HistoryExchangeActivity.this).getUserID())
                .addParam("merchantID", merchantID)
                .build()
                .execute(HistoryExchangeActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int points = jsonObject.getInt("points_card");
                                double exchangePoints = jsonObject.getDouble("points_citi");
                                String time = jsonObject.getString("time");
                                historyExchangeAdapter.addData(points, exchangePoints, time);
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
                        Toast.makeText(HistoryExchangeActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBefore() {
                        super.onBefore();
                        dialog = ProgressDialog.show(HistoryExchangeActivity.this, "", "正在获取历史记录...");
                    }
                });
    }

}
