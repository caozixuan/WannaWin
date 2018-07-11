package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AllCardActivity extends AppCompatActivity {

    ProgressDialog dialog;
    AllCardAdapter allCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_card);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_all_card);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置Adapter
        allCardAdapter = new AllCardAdapter(AllCardActivity.this);
        recyclerView.setAdapter(allCardAdapter);
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        dialog = ProgressDialog.show(AllCardActivity.this, "", "正在获取卡片列表...");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_all_card);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("全部卡列表");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        allCardAdapter.addData("Nike专营店","123");
        allCardAdapter.addData("ANTA专营店","1223");
        allCardAdapter.addData("LINING专营店","1233");
        dialog.dismiss();
    }
}
