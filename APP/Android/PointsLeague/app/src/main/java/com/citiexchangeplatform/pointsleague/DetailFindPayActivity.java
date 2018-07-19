package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailFindPayActivity extends AppCompatActivity {

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_find_pay);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail_find_pay);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        final String[] info = intent.getStringArrayExtra("itemInfo");

        TextView textViewName = (TextView) findViewById(R.id.textView_name_detail_find_pay);
        textViewName.setText(info[2]);
        TextView textViewDescription = (TextView) findViewById(R.id.textView_description_detail_find_pay);
        textViewDescription.setText("内容："+info[3]);
        TextView textViewTime = (TextView) findViewById(R.id.textView_time_detail_find_pay);
        textViewTime.setText("有效时间至"+info[4]);
        TextView textViewPoints = (TextView) findViewById(R.id.textView_points_detail_find_pay);
        textViewPoints.setText("消耗花旗点"+info[5]);
        Button button = (Button) findViewById(R.id.button_detail_find_pay);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailFindPayActivity.this);
                alertDialog.setTitle("支付积分").setMessage("确定要支付" + info[5] + "积分兑换吗")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        tryPay(info[0]);
                                    }
                                })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
    }

    private void tryPay(String itemID){
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/item/buy")
                .addParam("userID", LogStateInfo.getInstance(DetailFindPayActivity.this).getUserID())
                .addParam("itemID", itemID)
                .build()
                .execute(DetailFindPayActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);

                        boolean paySuccess = false;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            paySuccess = jsonObject.getBoolean("status");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();

                        if(paySuccess){
                            Toast.makeText(DetailFindPayActivity.this, "支付成功", Toast.LENGTH_LONG).show();
                            finish();
                        }else {
                            Toast.makeText(DetailFindPayActivity.this, "支付失败", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        dialog.dismiss();
                        Toast.makeText(DetailFindPayActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBefore() {
                        super.onBefore();
                        dialog = ProgressDialog.show(DetailFindPayActivity.this, "", "正在支付...");
                    }
                });
    }
}
