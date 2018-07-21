package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;

import org.json.JSONException;
import org.json.JSONObject;

public class BindCardActivity extends AppCompatActivity {

    private String merchantID;
    private String logoURL;
    private ProgressDialog dialog;

    ImageView imageViewLogo;
    EditText editTextAccount;
    EditText editTextPassword;
    Button buttonBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_card);

        toolBar();

        Intent intent = getIntent();
        merchantID = intent.getStringExtra("merchantID");
        logoURL = intent.getStringExtra("logoURL");

        imageViewLogo = (ImageView) findViewById(R.id.imageView_logo_bind_card);
        editTextAccount = (EditText) findViewById(R.id.editText_account_bind_card);
        editTextPassword = (EditText) findViewById(R.id.editText_password_bind_card);
        buttonBind = (Button) findViewById(R.id.button_bind_bind_card);

        showLogo();

        buttonBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextAccount.getText().length() == 0) {
                    showAlert("请输入账户");
                } else if (editTextPassword.getText().length() == 0) {
                    showAlert("请输入密码");
                } else {

                    String strAccount = editTextAccount.getText().toString();
                    String strPassword = editTextPassword.getText().toString();

                    tryBind(strAccount, strPassword);
                }
            }
        });
    }

    private void showLogo(){
        Glide.with(BindCardActivity.this)
                .load(logoURL)
                .placeholder(R.drawable.nike_logo)
                .error(R.drawable.nike_logo)
                .into(imageViewLogo);
    }

    private void showAlert(String msg) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(BindCardActivity.this);
        alertDialog.setTitle(msg).setPositiveButton("OK", null).show();
    }

    private void tryBind(String account, String password){
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/mscard/addcard")
                .addParam("userID", LogStateInfo.getInstance(BindCardActivity.this).getUserID())
                .addParam("merchantID", merchantID)
                .addParam("cardNum", account)
                .addParam("password", password)
                .build()
                .execute(BindCardActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        boolean bindSuccess = false;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            bindSuccess = jsonObject.getBoolean("status");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        dialog.dismiss();

                        if (bindSuccess) {
                            Toast.makeText(BindCardActivity.this, "绑定成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(BindCardActivity.this, "绑定失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        dialog.dismiss();
                        Toast.makeText(BindCardActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBefore() {
                        super.onBefore();
                        dialog = ProgressDialog.show(BindCardActivity.this, "", "绑定中...");
                    }
                });
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

        final TitleBar titleBar = (TitleBar) findViewById(R.id.title_bar);
        titleBar.setDividerColor(Color.GRAY);
        titleBar.setLeftImageResource(R.drawable.ic_left_black_24dp);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextColor(Color.BLACK);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleBar.setTitle("绑定卡片");
        titleBar.setTitleColor(Color.BLACK);

        //沉浸式
        titleBar.setImmersive(isImmersive);
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

}
