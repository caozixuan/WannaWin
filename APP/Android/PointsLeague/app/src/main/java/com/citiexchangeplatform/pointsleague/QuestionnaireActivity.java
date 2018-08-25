package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.VolleyError;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;

import org.json.JSONException;
import org.json.JSONObject;

public class QuestionnaireActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        initToolbar();
        initButton();
    }

    private void initToolbar(){
        TitleBar titleBar = (TitleBar) findViewById(R.id.toolbar_questionnaire);
        titleBar.setTitle("消费倾向");
        titleBar.setTitleColor(Color.BLACK);
    }

    private void initButton(){
        final ToggleButton toggleButton1 = findViewById(R.id.togglebutton_1);
        final ToggleButton toggleButton2 = findViewById(R.id.togglebutton_2);
        final ToggleButton toggleButton3 = findViewById(R.id.togglebutton_3);
        final ToggleButton toggleButton4 = findViewById(R.id.togglebutton_4);
        final ToggleButton toggleButton5 = findViewById(R.id.togglebutton_5);
        final ToggleButton toggleButton6 = findViewById(R.id.togglebutton_6);


        Button buttonFinish = findViewById(R.id.button_finish_questionnaire);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuestionnaireInfo(
                        toggleButton1.isChecked(), toggleButton2.isChecked(),
                        toggleButton3.isChecked(), toggleButton4.isChecked(),
                        toggleButton5.isChecked(), toggleButton6.isChecked() );
                finish();
            }
        });

        Button buttonIgnore = findViewById(R.id.button_ignore_questionnaire);
        buttonIgnore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuestionnaireInfo(false,false,false,false,false,false);
                finish();
            }
        });
    }

    private void setQuestionnaireInfo(boolean type1, boolean type2, boolean type3, boolean type4, boolean type5, boolean type6) {
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/recommend/initPref")
                .addParam("userID", LogStateInfo.getInstance(QuestionnaireActivity.this).getUserID())
                .addParam("type1", String.valueOf(type1))
                .addParam("type2", String.valueOf(type2))
                .addParam("type3", String.valueOf(type3))
                .addParam("type4", String.valueOf(type4))
                .addParam("type5", String.valueOf(type5))
                .addParam("type6", String.valueOf(type6))
                .build()
                .execute(QuestionnaireActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);

                        boolean haveSet = false;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            haveSet = jsonObject.getBoolean("status");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(haveSet){
                            Toast.makeText(QuestionnaireActivity.this,"消费倾向已提交",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(QuestionnaireActivity.this,"消费倾向提交失败",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        Toast.makeText(QuestionnaireActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }
                });
    }

}
