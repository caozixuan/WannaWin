package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PayingActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> data_posses_point;
    private List<Integer> business_image;
    private PayingAdapter mAdapter;
    private TextView Text_NeedPoints;
    private ImageView ImageView_Business;
    TextView Choose_Points;


    KeyListener storedKeylistener;
    // 存储勾选框状态的map集合
    private HashMap<Integer, Boolean> map = new HashMap<>();


    //接口实例
    //private RecyclerViewOnItemClickListener onItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paying);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_paying);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Text_NeedPoints = (TextView)findViewById(R.id.textView_points_need);
        //通过findViewById拿到RecyclerView实例
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_points);

        ImageView_Business = (ImageView)findViewById(R.id.imageView_business);
        Choose_Points = (TextView) findViewById(R.id.textview_points_choose);


        //初始化数据
        initData();

        //设置RecyclerView管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PayingAdapter(data_posses_point,business_image,map,getApplicationContext());

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
                if(isSoftShowing()){

                }
            }
        });


    }


    /*判断是否显示软键盘*/
    private boolean isSoftShowing() {
        //获取当前屏幕内容的高度
        int screenHeight = getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

        return screenHeight - rect.bottom != 0;
    }

    /*展示详情点击事件*/
    public void click_expand(View view){

        Intent intent = new Intent(this, PayingDetailsActivity.class);
        map = mAdapter.getMap();
        SerializableHashMap myMap=new SerializableHashMap();
        myMap.setMap(map);//将hashmap数据添加到封装的myMap中
        Bundle bundle = new Bundle();
        bundle.putSerializable("checkbox_map", myMap);
        intent.putExtras(bundle);

        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==1){
            //获得map
            Bundle bundle = getIntent().getExtras();
            SerializableHashMap serializableHashMap = (SerializableHashMap) bundle.get("checkbox_detail_map");
            map = serializableHashMap.getMap();

        }
    }

    /*确认抵扣按钮点击事件*/
    public void click_finish(View view){

        Intent intent = new Intent(this, PaymentFinishActivity.class);

        ArrayList<String> Points_Result = new ArrayList<>();
        ArrayList<Integer> Business_Image_Result = new ArrayList<>();

        if(Text_NeedPoints.getText().toString().equals(Choose_Points.getText().toString())){
            map = mAdapter.getMap();
            for (Integer i:map.keySet()){
                //map.keySet()返回的是所有key的值
                if(map.get(i)){
                    Points_Result.add(data_posses_point.get(i));
                    Business_Image_Result.add(business_image.get(i));
                }

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

    /*获得各项积分数据：商家图标、积分数*/
    protected void initData()
    {
        //设置需要的积分数
        Text_NeedPoints.setText("120");

        ImageView_Business.setImageResource(R.drawable.ic_store_24dp);

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


    private void sendRequestWithHttpURLConnection(){
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    //获取验证码
                    //URL url = new URL("http://193.112.44.141:8080/citi/login/getVCode");
                    //验证 验证码

                    //URL url = new URL("http://193.112.44.141:80/citi/login/login");
                    //URL url = new URL("http://193.112.44.141:80/citi/mscard/infos");
                    URL url = new URL("http://193.112.44.141:80/citi/merchant/getInfos");

                    //URL url = new URL("http://193.112.44.141:80/citi/mscard/cardtype");
                    connection = (HttpURLConnection) url.openConnection();
                    /*GET方法*/
                    //connection.setRequestMethod("GET");

                    connection.setRequestMethod("POST");
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    //out.writeBytes("userID=1ac9be07-f446-458c-b325-df2c7ecd113f&n=1");
                    out.writeBytes("start=0&n=2");
                    //out.writeBytes("merchantID=00001");
                    //out.writeBytes("phoneNum=12345678901&password=123456");
                    //out.writeBytes("start=0&n=2");
                    //out.writeBytes("phoneNum=17622833370&vcode=996428&password=987654");

                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    //下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }

                    System.out.println(response.toString());
                    /*json字符串最外层是方括号时：*/
                    //JSONArray jsonArray = new JSONArray(response.toString());
                    //for (int i = 0; i < jsonArray.length(); i++) {
                    //    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    //    String mid= jsonObject.getString("MType");
                    //    System.out.println(mid);
                    //}
                    /*json字符串最外层是大括号时：*/
                    //JSONObject jsonObject = new JSONObject(response.toString());
                    //String mid= jsonObject.getString("userID");
                    //String mcourse=jsonObject.getString("phoneNum");
                    //int generalPoint = jsonObject.getInt("generalPoints");
                    //int availablePoints = jsonObject.getInt("availablePoints");
                    //System.out.println(mid);

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (reader != null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }



}

