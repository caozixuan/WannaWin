package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    TextView textViewName;
    TextView textViewPoint;
    TextView textViewExchangePoint;
    TextView textViewAccount;
    TextView textViewAccount2;
    TextView textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_info);

        imageViewCard = findViewById(R.id.imageView_card_info);
        textViewName = findViewById(R.id.textView_name_card_info);
        textViewPoint = findViewById(R.id.textView_points_card_info);
        textViewExchangePoint = findViewById(R.id.textView_exchange_points_card_info);
        textViewAccount = findViewById(R.id.textView_account_card_info);
        textViewAccount2 = findViewById(R.id.textView_account2_card_info);
        //textViewDescription = findViewById(R.id.textView_description_card_info);

        Intent intent = getIntent();
        merchantID = intent.getStringExtra("merchantID");

        toolBar();

        int colors[] = { 0xff6bacfa , 0xff4EC0A3 };
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
        RelativeLayout relativeLayout = findViewById(R.id.relative_card_info);
        relativeLayout.setBackground(bg);


        Button buttonUnbind = (Button) findViewById(R.id.button_unbind_card_info);
        buttonUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CardInfoActivity.this);
                alertDialog.setTitle("解除绑定").setMessage("确定要解除该卡绑定吗")
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tryUnbind();
                                }
                            })
                    .setNegativeButton("取消", null)
                    .show();
            }
        });

        getInfos();

    }

    public void toolBar(){
        boolean isImmersive = false;
        if (hasKitKat() && !hasLollipop()) {
            isImmersive = true;
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        } else if (hasLollipop()) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    //| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            isImmersive = true;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        TitleBar titleBar = (TitleBar) findViewById(R.id.toolbar_card_info);
        titleBar.setLeftImageResource(R.drawable.ic_left_orange_24dp);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextColor(0xFFFF9546);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleBar.setTitle("卡详情");
        titleBar.setTitleColor(Color.BLACK);

        titleBar.setActionTextColor(0xFFFF9546);
        titleBar.addAction(new TitleBar.TextAction("兑换记录") {
            @Override
            public void performAction(View view) {
                Intent intentToHistory = new Intent(CardInfoActivity.this, HistoryExchangeActivity.class);
                intentToHistory.putExtra("merchantID",merchantID);
                startActivity(intentToHistory);
            }
        });
        //沉浸式
        titleBar.setImmersive(isImmersive);
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
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
                            String cardName = jsonObject.getString("merchantName");
                            String cardImageURL = jsonObject.getString("merchantLogoURL");
                            String cardLogoURL = jsonObject.getString("cardLogoURL");
                            String cardDescription = jsonObject.getString("cardDescription");
                            String cardNum = jsonObject.getString("cardNum");
                            int point = jsonObject.getInt("points");
                            int type = jsonObject.getInt("type");
                            double proportion = jsonObject.getDouble("proportion");


                            Glide.with(CardInfoActivity.this)
                                    .load(cardImageURL)
                                    .error(R.drawable.nike_logo)
                                    .into(imageViewCard);
                            textViewName.setText(cardName);
                            textViewPoint.setText(String.valueOf(point));
                            textViewExchangePoint.setText(String.format("%.1f",point/proportion));
                            textViewAccount.setText(cardNum);
                            textViewAccount2.setText(cardNum);
                            //textViewDescription.setText(cardDescription);

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

    private void tryUnbind(){
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/mscard/unbindcard")
                .addParam("userID", LogStateInfo.getInstance(CardInfoActivity.this).getUserID())
                .addParam("merchantID", merchantID)
                .addParam("cardNum", textViewAccount.getText().toString())
                .build()
                .execute(CardInfoActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        boolean unindSuccess = false;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            unindSuccess = jsonObject.getBoolean("status");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        dialog.dismiss();

                        if (unindSuccess) {
                            Toast.makeText(CardInfoActivity.this, "解除绑定成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(CardInfoActivity.this, "解除绑定失败", Toast.LENGTH_SHORT).show();
                        }
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
                        dialog = ProgressDialog.show(CardInfoActivity.this, "", "正在请求服务器...");
                    }
                });
    }
}
