package com.citiexchangeplatform.pointsleague;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaymentFinishActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> data_posses_point;
    private PayingFinishAdapter mAdapter;
    private TextView Text_NeedPoints;
    private ImageView ImageView_Business;
    private HashMap<Integer, Boolean> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_finish);

        //通过findViewById拿到RecyclerView实例
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_finish_points);
        Text_NeedPoints = (TextView)findViewById(R.id.textView_points_usedTotal);

        ImageView_Business = (ImageView)findViewById(R.id.imageView_finish_business);


        Bundle bundle = getIntent().getExtras();
        SerializableHashMap serializableHashMap = (SerializableHashMap) bundle.get("checkbox_map");
        map = serializableHashMap.getMap();


        //初始化数据
        initData();

        //设置RecyclerView管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PayingFinishAdapter(data_posses_point,getApplicationContext());

        mRecyclerView.setAdapter(mAdapter);




    }

    /*获得各项积分数据：商家图标、积分数*/
    protected void initData()
    {
        //设置需要的积分数
        Text_NeedPoints.setText("120p");

        ImageView_Business.setImageResource(R.drawable.nike_store);

        //设置列表项中的文字（用户拥有的积分数）
        data_posses_point = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            data_posses_point.add("" + (char) i);
        }
    }


}
