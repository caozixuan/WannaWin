package com.citiexchangeplatform.pointsleague;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        toolBar();




    }

    public void buttonClick(View view){
        Intent intent = new Intent(FeedbackActivity.this,FeedbackResultActivity.class);
        //判断输入为空情况

        TextView email = (TextView) findViewById(R.id.textView_feedback_email);

        if(email.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "邮箱不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(intent);

    }

    public void toolBar(){

        final TitleBar titleBar = (TitleBar) findViewById(R.id.title_bar);

        //左侧
        titleBar.setLeftImageResource(R.drawable.ic_left_orange_24dp);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextColor(getResources().getColor(R.color.colorLightOrange));

        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleBar.setTitle("反馈");
        titleBar.setTitleColor(Color.BLACK);

        titleBar.setActionTextColor(Color.BLACK);

    }

}
