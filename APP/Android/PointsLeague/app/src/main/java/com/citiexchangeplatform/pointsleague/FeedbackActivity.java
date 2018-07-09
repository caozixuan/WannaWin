package com.citiexchangeplatform.pointsleague;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        //设置toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_feedback);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    public void buttonClick(View view){
        Intent intent = new Intent(FeedbackActivity.this,FeedbackResultActivity.class);
        //判断输入为空情况
        TextView title = (TextView) findViewById(R.id.textView_feedback_title);
        TextView email = (TextView) findViewById(R.id.textView_feedback_email);

        if(title.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "标题不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(email.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "邮箱不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(intent);







    }
}
