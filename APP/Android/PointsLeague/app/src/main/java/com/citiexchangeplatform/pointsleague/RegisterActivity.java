package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.JsonObject;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class RegisterActivity extends AppCompatActivity {

    String newAccount;
    LinearLayout accountInfoLayout;
    private ProgressDialog dialog;
    private String strAccount;
    private String strPassword;
    private String strPasswordVerification;
    private String strMsgVerification;

    Button buttonMsg;
    Button buttonRegister;

    boolean getVerification = false;

    int remainSecond;
    final int WAITING_TIME_FOR_EACH_MSG = 30;


    /*private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (remainSecond == WAITING_TIME_FOR_EACH_MSG) {
                        enableButton(false, buttonMsg);
                    }
                    remainSecond--;
                    buttonMsg.setText(remainSecond + "秒后重试");
                    if (remainSecond != 0) {
                        Message message1 = new Message();
                        message1.what = 0;
                        this.sendMessageDelayed(message1, 1000);
                    } else {
                        buttonMsg.setText("获取验证码");
                        enableButton(true, buttonMsg);
                    }
            }
        }
    };*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //final EditText editTextAccount = (EditText) findViewById(R.id.editText_account_register);
        //final EditText editTextPassword = (EditText) findViewById(R.id.editText_password_register);
        //final EditText editTextPasswordVerification = (EditText) findViewById(R.id.editText_password_verification);
        //final EditText editTextMsgVerification = (EditText) findViewById(R.id.editText_msg_verification);
        //
        //buttonMsg = (Button) findViewById(R.id.button_get_verification_register);
        //buttonRegister = (Button) findViewById(R.id.button_register_register);
        //
        //buttonMsg.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        strAccount = editTextAccount.getText().toString();
        //        if (strAccount.length() != 11) {
        //            showAlert("请输入正确的电话号码");
        //        } else {
        //            getMsgVerification();
        //        }
        //    }
        //});
        //
        //buttonRegister.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        strPassword = editTextPassword.getText().toString();
        //        strPasswordVerification = editTextPasswordVerification.getText().toString();
        //        strMsgVerification = editTextMsgVerification.getText().toString();
        //
        //        if (!getVerification) {
        //            showAlert("请先获得验证码");
        //        } else if (!strAccount.equals(editTextAccount.getText().toString())) {
        //            Toast.makeText(RegisterActivity.this, "电话号码不匹配，注册失败", Toast.LENGTH_LONG).show();
        //        } else if (strPassword.isEmpty()){
        //            showAlert("请输入密码");
        //        } else if (!strPassword.equals(strPasswordVerification)) {
        //            showAlert("两次输入密码不相同");
        //        } else if (strMsgVerification.length() != 6) {
        //            showAlert("请输入正确的验证码");
        //        } else {
        //
        //            tryRegister();
        //        }
        //    }
        //});

        toolBar();
        accountInfoLayout = findViewById(R.id.register);

        final EditText editTextPhone = (EditText) findViewById(R.id.editText_phone_card_points);
        final EditText editTextMsg = (EditText) findViewById(R.id.editText_msg_card_points);

        final Button buttonMsg = (Button) findViewById(R.id.button_msg_card_points);
        buttonMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMsgVerification(editTextPhone.getText().toString(), buttonMsg);
            }
        });

        Button buttonNext = (Button) findViewById(R.id.button_next_card_points);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextPhone.getText().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                } else if (editTextMsg.getText().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "请输入短信验证码", Toast.LENGTH_SHORT).show();
                } else {

                    newAccount = editTextPhone.getText().toString();
                    String strMsg = editTextMsg.getText().toString();

                    checkMsgVerification(newAccount, strMsg);
                }
            }
        });

        Button buttonLogin = (Button) findViewById(R.id.button_back_login_card_points);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intentToLogin);
                finish();
            }
        });

    }

    private void getMsgVerification(String phone, final Button buttonMsg) {
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/account/getVCode")
                .addParam("phoneNum", phone)
                .build()
                .execute(RegisterActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);

                        boolean getMSg = false;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            getMSg = jsonObject.getBoolean("status");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (getMSg) {
                            Toast.makeText(RegisterActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();

                            remainSecond = WAITING_TIME_FOR_EACH_MSG;
                            Message messageChangeSecond = new Message();
                            messageChangeSecond.what = 0;
                            new Handler() {
                                @Override
                                public void handleMessage(Message message) {
                                    switch (message.what) {
                                        case 0:
                                            if (remainSecond == WAITING_TIME_FOR_EACH_MSG) {
                                                buttonMsg.setEnabled(false);
                                            }
                                            remainSecond--;
                                            buttonMsg.setText(remainSecond + "秒后重试");
                                            if (remainSecond != 0) {
                                                Message message1 = new Message();
                                                message1.what = 0;
                                                this.sendMessageDelayed(message1, 1000);
                                            } else {
                                                buttonMsg.setText("获取验证码");
                                                buttonMsg.setEnabled(true);
                                            }
                                    }
                                }
                            }.sendMessage(messageChangeSecond);
                        }else {
                            Toast.makeText(RegisterActivity.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        Toast.makeText(RegisterActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void checkMsgVerification(String strPhone, String strMsg){
        verifyVCode(strPhone,strMsg);

    }

    private void loadRegisterContent2(){

        View content = LayoutInflater.from(RegisterActivity.this).inflate(R.layout.content_register2_points, null);
        accountInfoLayout.addView(content);

        final EditText editTextPassword1 = (EditText) findViewById(R.id.editText_password1_card_points);
        final EditText editTextPassword2 = (EditText) findViewById(R.id.editText_password2_card_points);



        Button buttonRegister = (Button) findViewById(R.id.button_register_card_points);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextPassword1.getText().length() == 0 || editTextPassword2.getText().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if ( ! editTextPassword1.getText().toString().equals(editTextPassword2.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                } else {
                    tryRegister(newAccount, editTextPassword1.getText().toString());
                }
            }
        });

        Button buttonLogin = (Button) findViewById(R.id.button_back_login_card_points);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToRegister = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intentToRegister);
                finish();
            }
        });
    }

    private void verifyVCode(final String strAccount, final String vCode){
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/account/vfcode")
                .addParam("phoneNum", strAccount)
                .addParam("vcode", vCode)
                .build()
                .execute(RegisterActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        boolean registerSuccess = false;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            registerSuccess = jsonObject.getBoolean("status");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (registerSuccess) {
                            //Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            //tryLogin(strAccount, strPassword);
                            accountInfoLayout.removeAllViewsInLayout();
                            loadRegisterContent2();
                        }else {
                            Toast.makeText(RegisterActivity.this, "验证码错误，请重新输入", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        Toast.makeText(RegisterActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void tryRegister(final String phoneNum, final String password){
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/account/resetPassword")
                .addParam("phoneNum", phoneNum)
                .addParam("newPassword", password)
                .build()
                .execute(RegisterActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        boolean registerSuccess = false;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            registerSuccess = jsonObject.getBoolean("status");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (registerSuccess) {
                            strPassword = password;
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            tryLogin(newAccount, strPassword);

                        }else {
                            Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        Toast.makeText(RegisterActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void tryLogin(final String account, String password) {
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/account/login")
                .addParam("phoneNum", account)
                .addParam("password", password)
                .build()
                .execute(RegisterActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        boolean logSuccess = false;
                        try {
                            if (response.length() > 2) {
                                JSONObject jsonObject = new JSONObject(response);

                                String accountPhoneNum = jsonObject.getString("phoneNum");
                                String userID = jsonObject.getString("userID");

                                LogStateInfo.getInstance(RegisterActivity.this)
                                        .setAccount(account)
                                        .setUserID(userID);

                                logSuccess = true;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        dialog.dismiss();

                        if (logSuccess) {
                            LogStateInfo.getInstance(RegisterActivity.this).login();
                            Toast.makeText(RegisterActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            onResume();
                            getQuestionnaireInfo();
                        } else {
                            Toast.makeText(RegisterActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        dialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBefore() {
                        super.onBefore();
                        dialog = ProgressDialog.show(RegisterActivity.this, "", "登录中...");
                    }
                });
    }

    private void getQuestionnaireInfo() {
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/recommend/isInvestigated")
                .addParam("userID", LogStateInfo.getInstance(RegisterActivity.this).getUserID())
                .build()
                .execute(RegisterActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);

                        boolean haveDone = false;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            haveDone = jsonObject.getBoolean("status");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(!haveDone){
                            Intent intentToQuestionnaire = new Intent(RegisterActivity.this, QuestionnaireActivity.class);
                            startActivity(intentToQuestionnaire);
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        Toast.makeText(RegisterActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }
                });
    }
    /*private void showAlert(String msg) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterActivity.this);
        alertDialog.setTitle(msg).setPositiveButton("OK", null).show();
    }

    private void getMsgVerification() {
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/account/getVCode")
                .addParam("phoneNum", strAccount)
                .build()
                .execute(RegisterActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);

                        boolean getMSg = false;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            getMSg = jsonObject.getBoolean("status");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (getMSg) {
                            getVerification = true;
                            Toast.makeText(RegisterActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();

                            remainSecond = WAITING_TIME_FOR_EACH_MSG;
                            Message messageChangeSecond = new Message();
                            messageChangeSecond.what = 0;
                            handler.sendMessage(messageChangeSecond);
                        }else {
                            Toast.makeText(RegisterActivity.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        dialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBefore() {
                        super.onBefore();
                        dialog = ProgressDialog.show(RegisterActivity.this, "", "正在获得验证码...");
                    }
                });
    }

    private void tryRegister(){
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/account/sendVCode")
                .addParam("phoneNum", strAccount)
                .addParam("vcode", strMsgVerification)
                .addParam("password", strPassword)
                .build()
                .execute(RegisterActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        boolean registerSuccess = false;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            registerSuccess = jsonObject.getBoolean("status");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (registerSuccess) {
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }

                        dialog.dismiss();
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        dialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBefore() {
                        super.onBefore();
                        dialog = ProgressDialog.show(RegisterActivity.this, "", "正在注册...");
                    }
                });
    }

    private void enableButton(boolean isEnable, Button button) {
        if (isEnable) {
            button.setEnabled(true);
            button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else {
            button.setEnabled(false);
            button.setBackgroundColor(getResources().getColor(R.color.colorUnable));
        }
    }*/

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
        titleBar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        titleBar.setLeftImageResource(R.drawable.ic_left_orange_24dp);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextSize(18);
        titleBar.setLeftTextColor(getResources().getColor(R.color.colorLightOrange));
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

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
