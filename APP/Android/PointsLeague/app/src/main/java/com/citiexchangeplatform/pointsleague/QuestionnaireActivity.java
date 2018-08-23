package com.citiexchangeplatform.pointsleague;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

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
        ToggleButton toggleButton1 = findViewById(R.id.togglebutton_1);
        ToggleButton toggleButton2 = findViewById(R.id.togglebutton_2);
        ToggleButton toggleButton3 = findViewById(R.id.togglebutton_3);
        ToggleButton toggleButton4 = findViewById(R.id.togglebutton_4);
        ToggleButton toggleButton5 = findViewById(R.id.togglebutton_5);
        ToggleButton toggleButton6 = findViewById(R.id.togglebutton_6);


        Button buttonFinish = findViewById(R.id.button_finish_questionnaire);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button buttonIgnore = findViewById(R.id.button_ignore_questionnaire);
        buttonIgnore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
