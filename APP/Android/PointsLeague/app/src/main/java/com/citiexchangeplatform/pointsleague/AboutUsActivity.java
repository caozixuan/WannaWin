package com.citiexchangeplatform.pointsleague;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        toolBar();

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

        titleBar.setTitle("关于我们");
        titleBar.setTitleColor(Color.BLACK);

        titleBar.setActionTextColor(Color.BLACK);

    }
}
