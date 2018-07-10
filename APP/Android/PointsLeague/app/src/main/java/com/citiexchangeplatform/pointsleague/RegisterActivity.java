package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
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

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (remainSecond == WAITING_TIME_FOR_EACH_MSG) {
                        enableButton(false, buttonMsg);
                    }
                    remainSecond--;
                    buttonMsg.setText(remainSecond + "秒后重试");
                    if(remainSecond != 0) {
                        Message message1 = new Message();
                        message1.what = 1;
                        this.sendMessageDelayed(message1, 1000);
                    }
                    else {
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

        final EditText editTextAccount = (EditText)findViewById(R.id.editText_account_register);
        final EditText editTextPassword = (EditText)findViewById(R.id.editText_password_register);
        final EditText editTextPasswordVerification = (EditText)findViewById(R.id.editText_password_verification);
        final EditText editTextMsgVerification = (EditText)findViewById(R.id.editText_msg_verification);

        buttonMsg = (Button)findViewById(R.id.button_get_verification_register);
        buttonRegister = (Button)findViewById(R.id.button_register_register);


        buttonMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strAccount = editTextAccount.getText().toString();
                if(strAccount.length() != 11){
                    showAlert("请输入正确的电话号码");
                }else {
                    dialog = ProgressDialog.show(RegisterActivity.this, "", "正在获得验证码...");
                    new Thread(new RegisterActivity.getMsgVerification()).start();
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strPassword = editTextPassword.getText().toString();
                strPasswordVerification = editTextPasswordVerification.getText().toString();
                strMsgVerification = editTextMsgVerification.getText().toString();


                if(!getVerification){
                    showAlert("请先获得验证码");
                }
                else if(!strAccount.equals(editTextAccount.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"电话号码不匹配，注册失败",Toast.LENGTH_LONG).show();
                }else if (!strPassword.equals(strPasswordVerification)){
                    showAlert("两次输入密码不相同");
                }else if(strMsgVerification.length() != 6){
                    showAlert("请输入正确的验证码");
                }else {
                    dialog = ProgressDialog.show(RegisterActivity.this, "", "正在注册...");
                    new Thread(new RegisterActivity.tryRegister()).start();
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

    private void showAlert(String msg){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterActivity.this);
        alertDialog.setTitle(msg).setPositiveButton("OK", null).show();
    }

    class getMsgVerification implements Runnable {

        @Override
        public void run() {
            boolean getSuccess = false;

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL("http://193.112.44.141:80/citi/login/getVCode");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");

                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes("phoneNum=" + strAccount);

                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                InputStream in = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in));
                String json = reader.readLine();
                System.out.println(json);
                if(json.length() == 2)
                    getSuccess = true;

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null){
                    connection.disconnect();
                }
            }

            dialog.dismiss();

            if(getSuccess){
                getVerification = true;
                remainSecond = WAITING_TIME_FOR_EACH_MSG;
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);

            }else {
                Looper.prepare();
                Toast.makeText(RegisterActivity.this, "获得短信验证码失败", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }
    }


    class tryRegister implements Runnable {

        @Override
        public void run() {
            boolean registerSuccess = false;

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL("http://193.112.44.141:80/citi/login/sendVCode");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");

                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes("phoneNum=" + strAccount + "&vcode=" + strMsgVerification + "&password=" + strPassword);

                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                InputStream in = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in));
                String json = reader.readLine();
                System.out.println(json);
                JSONObject jsonObject = new JSONObject(json);
                registerSuccess = jsonObject.getBoolean("isCreate");

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null){
                    connection.disconnect();
                }
            }

            dialog.dismiss();
            if(registerSuccess){
                //返回并填充账户
                finish();

            }else {
                Looper.prepare();
                Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }
    }

    private void enableButton(boolean isEnable, Button button){
        if(isEnable){
            button.setEnabled(true);
            button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }else {
            button.setEnabled(false);
            button.setBackgroundColor(getResources().getColor(R.color.colorUnable));
        }
    }
}
