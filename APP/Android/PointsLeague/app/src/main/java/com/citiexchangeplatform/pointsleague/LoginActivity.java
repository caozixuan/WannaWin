package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    private EditText editTextAccount;
    private EditText editTextPassword;
    private String strAccount;
    private String strPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextAccount = (EditText) findViewById(R.id.editText_account_login);
        editTextPassword = (EditText) findViewById(R.id.editText_password_login);

        Button buttonLogin = (Button)findViewById(R.id.button_login_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextAccount.getText().length() == 0){
                    showAlert("请输入账户");
                }else if (editTextPassword.getText().length() == 0){
                    showAlert("请输入密码");
                }
                else {

                    strAccount = editTextAccount.getText().toString();
                    strPassword = editTextPassword.getText().toString();

                    dialog = ProgressDialog.show(LoginActivity.this, "", "登录中...");
                    new Thread(new tryLogin()).start();
                }
            }
        });

        Button buttonRegister = (Button)findViewById(R.id.button_register_login);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentToRegister);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void showAlert(String msg){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
        alertDialog.setTitle(msg).setPositiveButton("OK", null).show();
    }


    class tryLogin implements Runnable {

        @Override
        public void run() {
            boolean logSuccess = false;

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL("http://193.112.44.141:80/citi/login");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes("phoneNum=" + strAccount + "&password=" + strPassword);
                connection.setConnectTimeout(8000);
                connection.setReadTimeout(8000);

                InputStream in = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in));
                String json = reader.readLine();
                System.out.println(json);

                if(json.length() > 2){
                    logSuccess = true;
                    JSONObject jsonObject = new JSONObject(json);

                    String accountPhoneNum = jsonObject.getString("phoneNum");
                    String userID = jsonObject.getString("userID");
                    int generalPoint = jsonObject.getInt("generalPoints");
                    int availablePoints = jsonObject.getInt("availablePoints");

                    LogStateInfo.getInstance(LoginActivity.this).setAccount(accountPhoneNum)
                            .setUserID(userID)
                            .setGeneralPoint(generalPoint)
                            .setAvailablePoints(availablePoints);
                }
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
            if(logSuccess){
                LogStateInfo.getInstance(LoginActivity.this).login();
                finish();

            }else {
                Looper.prepare();
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }
    }

}
