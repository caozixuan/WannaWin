package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


    private Handler handler = new Handler() {
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
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText editTextAccount = (EditText) findViewById(R.id.editText_account_register);
        final EditText editTextPassword = (EditText) findViewById(R.id.editText_password_register);
        final EditText editTextPasswordVerification = (EditText) findViewById(R.id.editText_password_verification);
        final EditText editTextMsgVerification = (EditText) findViewById(R.id.editText_msg_verification);

        buttonMsg = (Button) findViewById(R.id.button_get_verification_register);
        buttonRegister = (Button) findViewById(R.id.button_register_register);

        buttonMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strAccount = editTextAccount.getText().toString();
                if (strAccount.length() != 11) {
                    showAlert("请输入正确的电话号码");
                } else {

                    getMsgVerification();
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strPassword = editTextPassword.getText().toString();
                strPasswordVerification = editTextPasswordVerification.getText().toString();
                strMsgVerification = editTextMsgVerification.getText().toString();

                if (!getVerification) {
                    showAlert("请先获得验证码");
                } else if (!strAccount.equals(editTextAccount.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "电话号码不匹配，注册失败", Toast.LENGTH_LONG).show();
                } else if (!strPassword.equals(strPasswordVerification)) {
                    showAlert("两次输入密码不相同");
                } else if (strMsgVerification.length() != 6) {
                    showAlert("请输入正确的验证码");
                } else {

                    tryRegister();
                }
            }
        });

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_register);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showAlert(String msg) {
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
    }
}
