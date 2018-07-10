package com.citiexchangeplatform.pointsleague;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralSettingActivity extends AppCompatActivity {


    //通用设置中的项目
    private String[] data = { "字体大小", "流量统计", "功能", "存储空间",
            "清除缓存" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_setting);


        //设置toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_general_setting);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                GeneralSettingActivity.this, android.R.layout.simple_expandable_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.listView_setting);
        listView.setAdapter(adapter);

    }
}
