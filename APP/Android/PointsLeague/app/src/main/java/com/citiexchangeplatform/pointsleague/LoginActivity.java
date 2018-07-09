package com.citiexchangeplatform.pointsleague;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText editTextAccount = (EditText) findViewById(R.id.editText_account);
        final EditText editTextPassword = (EditText) findViewById(R.id.editText_password);
        final EditText editTextVerification = (EditText) findViewById(R.id.editText_verification);

        Button buttonLogin = (Button)findViewById(R.id.button_login_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextAccount.getText().length() == 0){
                    showAlert("请输入账户");
                }else if (editTextPassword.getText().length() == 0){
                    showAlert("请输入密码");
                }
                else if (editTextVerification.getText().length() == 0){
                    showAlert("请输入短信验证码");
                }
                else {
                    LogStateInfo.getInstance(LoginActivity.this).login().setAccount(editTextAccount.getText().toString());

                    Toast.makeText(LoginActivity.this, "login successfully", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        Button buttonRegister = (Button)findViewById(R.id.button_register_login);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextAccount.getText().length() == 0){
                    showAlert("请输入账户");
                }else if (editTextPassword.getText().length() == 0){
                    showAlert("请输入密码");
                }
                else if (editTextVerification.getText().length() == 0){
                    showAlert("请输入短信验证码");
                }
                else {
                    LogStateInfo.getInstance(LoginActivity.this).login().setAccount(editTextAccount.getText().toString());

                    Toast.makeText(LoginActivity.this, "login successfully", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }

    private void showAlert(String errorMsg){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
        alertDialog.setTitle(errorMsg).setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }
}
