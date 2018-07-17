package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CardInfoActivity extends AppCompatActivity {

    private String merchantID;
    private ProgressDialog dialog;

    ImageView imageViewCard;
    TextView textViewPoint;
    TextView textViewExchangePoint;
    TextView textViewAccount;
    TextView textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_info);

        imageViewCard = findViewById(R.id.imageView_card_info);
        textViewPoint = findViewById(R.id.textView_points_card_info);
        textViewExchangePoint = findViewById(R.id.textView_exchange_points_card_info);
        textViewAccount = findViewById(R.id.textView_account_card_info);
        textViewDescription = findViewById(R.id.textView_description_card_info);

        Intent intent = getIntent();
        merchantID = intent.getStringExtra("merchantID");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_card_info);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView textViewHistory = (TextView) findViewById(R.id.textView_history_exchange_card_info);
        textViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToHistory = new Intent(CardInfoActivity.this, HistoryExchangeActivity.class);
                startActivity(intentToHistory);
            }
        });

        getInfos();
    }

    private void getInfos() {
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/mscard/getDetailCard")
                .addParam("userID", LogStateInfo.getInstance(CardInfoActivity.this).getUserID())
                .addParam("merchantID", merchantID)
                .build()
                .execute(CardInfoActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String cardLogoURL = jsonObject.getString("cardLogoURL");
                            String cardDescription = jsonObject.getString("cardDescription");
                            String cardNum = jsonObject.getString("cardNum");
                            int point = jsonObject.getInt("points");
                            int type = jsonObject.getInt("type");
                            double proportion = jsonObject.getDouble("proportion");


                            Glide.with(CardInfoActivity.this)
                                    .load(cardLogoURL)
                                    .placeholder(R.drawable.store_card)
                                    .error(R.drawable.store_card)
                                    .into(imageViewCard);
                            textViewPoint.setText(String.valueOf(point));
                            textViewExchangePoint.setText(String.format("%.1f",point/proportion));
                            textViewAccount.setText(cardNum);
                            textViewDescription.setText(cardDescription);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        dialog.dismiss();
                        Toast.makeText(CardInfoActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBefore() {
                        super.onBefore();
                        dialog = ProgressDialog.show(CardInfoActivity.this, "", "正在获取卡信息...");
                    }
                });
    }
}
