/*
package com.citiexchangeplatform.pointsleague;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.method.KeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PayingDetailsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private RecyclerView mRecyclerView;
    private List<String> data_posses_point;
    private List<Integer> business_image;
    private PayingAdapter mAdapter;
    private TextView Text_NeedPoints;
    TextView Choose_Points;

    KeyListener storedKeylistener;
    // 存储勾选框状态的map集合
    private HashMap<Integer, Double> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paying_details);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_paying_details);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //通过findViewById拿到RecyclerView实例
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_paying_details);

        Choose_Points = (TextView) findViewById(R.id.textview_details_points_choose);

        //初始化数据
        initData();

        //设置RecyclerView管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PayingAdapter(PayingDetailsActivity.this);

        mRecyclerView.setAdapter(mAdapter);
        //添加Android自带的分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        mAdapter.buttonSetOnclick(new PayingAdapter.ButtonInterface() {
            @Override
            public void onclick(View view, int position) {
                //Toast.makeText(getApplicationContext(), "点击条目上的按钮"+position, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), data_posses_point.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.checkBoxSetOnclick(new PayingAdapter.CheckBoxInterface() {
            @Override
            public void onclick(View view, int position) {
                Choose_Points.setText(String.valueOf(mAdapter.getTotal()));

            }
        });

        Button Expand_Less = (Button) findViewById(R.id.button_expand_less);
        Expand_Less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    */
/*收起列表*//*

    public void click_expand_less(View view){
        //Intent intent = new Intent(this, PayingActivity.class);
        //startActivity(intent);
        finish();
    }

    */
/*确认抵扣按钮点击事件*//*

    public void click_finish(View view){
        Button Finish_Button = (Button) findViewById(R.id.button_finish);
        Intent intent = new Intent(this, PaymentFinishActivity.class);

        ArrayList<String> Points_Result = new ArrayList<>();
        ArrayList<Integer> Business_Image_Result = new ArrayList<>();

        if(Text_NeedPoints.getText().toString().equals(Choose_Points.getText().toString())){
            map = mAdapter.getMap();
            for (Integer i:map.keySet()){
                //map.keySet()返回的是所有key的值

                    Points_Result.add(data_posses_point.get(i));
                    Business_Image_Result.add(business_image.get(i));


            }
            Bundle bundle = new Bundle();


            //intent.putExtra("value", (Serializable)map);

            SerializableHashMap myMap=new SerializableHashMap();
            myMap.setMap(map);//将hashmap数据添加到封装的myMap中

            bundle.putStringArrayList("points_result",Points_Result);
            bundle.putIntegerArrayList("image_resource",Business_Image_Result);
            //bundle.putSerializable("checkbox_map", myMap);
            intent.putExtras(bundle);

            startActivity(intent);
        }
        else{
            int diffPoints = Integer.parseInt(Text_NeedPoints.getText().toString()) - Integer.parseInt(Choose_Points.getText().toString());
            Toast.makeText(getApplicationContext(), "所选积分不足，还需"+diffPoints, Toast.LENGTH_SHORT).show();
        }



    }

    */
/*获得各项积分数据：商家图标、积分数*//*

    protected void initData()
    {
        //设置需要的积分数
        //Text_NeedPoints.setText("120");


        //设置列表项中的文字（用户拥有的积分数）
        data_posses_point = new ArrayList<String>();
        for (int i = 0; i < 20; i++)
        {
            data_posses_point.add("" + i);
        }

        //设置选择积分栏目中商家logo
        business_image = new ArrayList<Integer>();

        for (int i = 0; i < data_posses_point.size(); i++)
        {
            business_image.add(R.drawable.ic_mall_black_24dp);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        //final List<WordModel> filteredModelList = filter(mModels, query);
        //mAdapter.edit()
        //        .replaceAll(filteredModelList)
        //        .commit();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    */
/*private static List<WordModel> filter(List<WordModel> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<WordModel> filteredModelList = new ArrayList<>();
        for (WordModel model : models) {
            final String text = model.getWord().toLowerCase();
            final String rank = String.valueOf(model.getRank());
            if (text.contains(lowerCaseQuery) || rank.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }*//*




}
*/
