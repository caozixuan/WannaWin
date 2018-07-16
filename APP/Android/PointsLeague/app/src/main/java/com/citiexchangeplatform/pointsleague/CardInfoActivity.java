package com.citiexchangeplatform.pointsleague;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class CardInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_card_info);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView textViewHistory = (TextView) findViewById(R.id.textView_history_exchange_card_info);
        textViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to history activity
            }
        });
    }
}
