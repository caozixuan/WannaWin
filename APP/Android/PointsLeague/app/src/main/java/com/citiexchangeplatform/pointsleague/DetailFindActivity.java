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

import org.json.JSONException;
import org.json.JSONObject;

public class DetailFindActivity extends AppCompatActivity {

    String merchantID;

    ProgressDialog dialog;

    ImageView imageViewLogo;
    TextView textViewName;
    TextView textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_find);

        Intent intent = getIntent();
        merchantID = intent.getStringExtra("merchantID");

        imageViewLogo = (ImageView) findViewById(R.id.imageView_logo_detail_find);
        textViewName = (TextView) findViewById(R.id.textView_name_detail_find);
        textViewDescription = (TextView) findViewById(R.id.textView_description_detail_find);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail_find);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getInfos();

    }


    private void getInfos() {
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/merchant/getSingleInfo")
                .addParam("merchantID", merchantID)
                .build()
                .execute(DetailFindActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String name = jsonObject.getString("name");
                            String description = jsonObject.getString("description");
                            String merchantLogoURL = jsonObject.getString("merchantLogoURL");


                            Glide.with(DetailFindActivity.this)
                                    .load(merchantLogoURL)
                                    .placeholder(R.drawable.store_card)
                                    .error(R.drawable.store_card)
                                    .into(imageViewLogo);
                            textViewName.setText(String.valueOf(name));
                            textViewDescription.setText(description);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        dialog.dismiss();
                        Toast.makeText(DetailFindActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBefore() {
                        super.onBefore();
                        dialog = ProgressDialog.show(DetailFindActivity.this, "", "正在获取商家信息...");
                    }
                });
    }
}
