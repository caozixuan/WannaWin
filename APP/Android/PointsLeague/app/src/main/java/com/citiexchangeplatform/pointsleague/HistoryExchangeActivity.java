package com.citiexchangeplatform.pointsleague;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class HistoryExchangeActivity extends AppCompatActivity {

    HistoryExchangeAdapter historyExchangeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_exchange);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_history_exchange);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        historyExchangeAdapter = new HistoryExchangeAdapter(HistoryExchangeActivity.this);
        recyclerView.setAdapter(historyExchangeAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_history_exchange);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        historyExchangeAdapter.addData(2000, 1000, "2018-7-15");
        historyExchangeAdapter.addData(300, 100, "2018-7-14");
        historyExchangeAdapter.addData(1500, 700, "2018-7-14");
        historyExchangeAdapter.addData(800, 1200, "2018-7-13");
    }
}
