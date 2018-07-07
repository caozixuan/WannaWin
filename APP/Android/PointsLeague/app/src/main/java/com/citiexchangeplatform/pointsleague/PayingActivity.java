package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayingActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> data_posses_point;
    private PayingAdapter mAdapter;
    private TextView Text_NeedPoints;
    private ImageView ImageView_Business;


    KeyListener storedKeylistener;
    // 存储勾选框状态的map集合
    private HashMap<Integer, Boolean> map = new HashMap<>();


    //接口实例
    //private RecyclerViewOnItemClickListener onItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paying);


        Text_NeedPoints = (TextView)findViewById(R.id.textView_points_need);
        //通过findViewById拿到RecyclerView实例
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_points);

        ImageView_Business = (ImageView)findViewById(R.id.imageView_business);


        //初始化数据
        initData();

        //设置RecyclerView管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PayingAdapter(data_posses_point,getApplicationContext());

        mRecyclerView.setAdapter(mAdapter);

        mAdapter.buttonSetOnclick(new PayingAdapter.ButtonInterface() {
            @Override
            public void onclick(View view, int position) {
                Toast.makeText(getApplicationContext(), "点击条目上的按钮"+position, Toast.LENGTH_SHORT).show();
            }
        });


    }


    /*按钮点击事件*/
    public void click_finish(View view){
        Button Finish_Button = (Button) findViewById(R.id.button_finish);
        Intent intent = new Intent(this, PaymentFinishActivity.class);

        map = mAdapter.getMap();
        Bundle bundle = new Bundle();


        //intent.putExtra("value", (Serializable)map);

        SerializableHashMap myMap=new SerializableHashMap();
        myMap.setMap(map);//将hashmap数据添加到封装的myMap中

        bundle.putSerializable("checkbox_map", myMap);
        intent.putExtras(bundle);

        startActivity(intent);

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

