package com.citiexchangeplatform.pointsleague;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PayingActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView msCardRecyclerView;
    private PayingAdapter mAdapter;

    TextView Choose_Points;

    KeyListener storedKeylistener;

    // 存储勾选框状态的map集合
    private HashMap<Integer, Double> map = new HashMap<>();

    //接口实例
    //private RecyclerViewOnItemClickListener onItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paying);


        //设置toolbar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_paying);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //获取页面中的元素
        initView();

        //设置RecyclerView管理器
        setRecyclerView();

        //获得初始化数据
        initData();

    }

    private void initView(){
        //会员卡列表
        msCardRecyclerView = (RecyclerView) findViewById(R.id.rv_points);
        //用户已选择积分
        Choose_Points = (TextView) findViewById(R.id.textview_points_choose);
    }

    /*获得各项积分卡数据：logo merchantName posses_points rate generalPoints*/
    protected void initData()
    {
        mAdapter.addData("1000", "100","0.1","中国移动","http://www.never-give-it-up.top/wp-content/uploads/2018/07/apple_logo.png");
        mAdapter.addData("2000","20","0.01","中国联通","http://www.never-give-it-up.top/wp-content/uploads/2018/07/yidong_logo.png");
        mAdapter.addData("3000","30","0.01","Nike","http://www.never-give-it-up.top/wp-content/uploads/2018/07/nike_logo.png");
        mAdapter.addData("1000", "100","0.1","中国移动","http://www.never-give-it-up.top/wp-content/uploads/2018/07/apple_logo.png");
        mAdapter.addData("2000","20","0.01","中国联通","http://www.never-give-it-up.top/wp-content/uploads/2018/07/yidong_logo.png");
        mAdapter.addData("3000","30","0.01","Nike","http://www.never-give-it-up.top/wp-content/uploads/2018/07/nike_logo.png");
        mAdapter.addData("1000", "100","0.1","中国移动","http://www.never-give-it-up.top/wp-content/uploads/2018/07/apple_logo.png");
        mAdapter.addData("2000","20","0.01","中国联通","http://www.never-give-it-up.top/wp-content/uploads/2018/07/yidong_logo.png");
        mAdapter.addData("3000","30","0.01","Nike","http://www.never-give-it-up.top/wp-content/uploads/2018/07/nike_logo.png");
        mAdapter.addData("1000", "100","0.1","中国移动","http://www.never-give-it-up.top/wp-content/uploads/2018/07/apple_logo.png");
        mAdapter.addData("2000","20","0.01","中国联通","http://www.never-give-it-up.top/wp-content/uploads/2018/07/yidong_logo.png");
        mAdapter.addData("3000","30","0.01","Nike","http://www.never-give-it-up.top/wp-content/uploads/2018/07/nike_logo.png");
        mAdapter.addData("3000","30","0.01","Nike","http://www.never-give-it-up.top/wp-content/uploads/2018/07/nike_logo.png");
        //getMSCardInfoRequest();



    }

    protected void setRecyclerView(){
        msCardRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PayingAdapter(PayingActivity.this);

        msCardRecyclerView.setAdapter(mAdapter);
        //添加Android自带的分割线
        msCardRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        mAdapter.buttonSetOnclick(new PayingAdapter.ButtonInterface() {
            @Override
            public void onclick(View view, int position) {
                //修改合计价格
                Choose_Points.setText(String.valueOf(mAdapter.getTotal()));
                //Toast.makeText(getApplicationContext(), "点击条目上的按钮"+position, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "修改", Toast.LENGTH_SHORT).show();
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


    /*确认抵扣按钮点击事件*/
    public void click_finish(View view){

        Intent intent = new Intent(this, PaymentFinishActivity.class);
        //
        ArrayList<String> used = new ArrayList<>();
        ArrayList<String> exchanged = new ArrayList<>();
        ArrayList<String> logos = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        //
        //map = mAdapter.getMap();
        for (int i = 0;i<mAdapter.getSourceItems().size();i++){
            if (mAdapter.getSourceItems().get(i).getChoose()){
                //map.keySet()返回的是所有key的值
                used.add(mAdapter.getSourceItems().get(i).getExchangePoint());
                logos.add(mAdapter.getSourceItems().get(i).getLogo());
                names.add(mAdapter.getSourceItems().get(i).getName());
                exchanged.add(mAdapter.getSourceItems().get(i).getTargetPoint());

            }
        }

        Bundle bundle = new Bundle();
        //
        //
        //intent.putExtra("value", (Serializable)map);
        //
        //SerializableHashMap myMap=new SerializableHashMap();
        //myMap.setMap(map);//将hashmap数据添加到封装的myMap中
        //
        bundle.putStringArrayList("points_used",used);
        bundle.putStringArrayList("points_exchanged",exchanged);
        bundle.putStringArrayList("logo_urls",logos);
        bundle.putStringArrayList("business_names",names);
        bundle.putDouble("total",mAdapter.getTotal());
        //bundle.putSerializable("checkbox_map", myMap);
        intent.putExtras(bundle);
        //
        startActivity(intent);


    }


    private void postStringRequest() {
        String url="http://193.112.44.141:80/citi/merchant/getInfos";
        RequestQueue queue = MyApplication.getHttpQueues();
        //RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("success",s);
                System.out.println(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("start","0");
                map.put("n", "1");
                return map;
            }
        };
        queue.add(request);
    }

    private void getMSCardInfoRequest() {
        String url="http://193.112.44.141:80/citi/mscard/infos";
        RequestQueue queue = MyApplication.getHttpQueues();
        //RequestQueue queue=Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("success",s);
                System.out.println(s);
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jObj = jsonArray.getJSONObject(i);

                        mAdapter.addData(jObj.getString("generalPoints"),jObj.getString("availablePoints"),jObj.getString("rate"),jObj.getString("merchantName"),jObj.getString("logoURL"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("userId",LogStateInfo.getInstance(PayingActivity.this).getUserID());
                map.put("n", "10");
                return map;
            }
        };
        queue.add(request);
    }

    private void postRequest() {
        //RequestQueue queue = VolleySingleton.getVolleySingleton(this.getApplicationContext()).getRequestQueue();
        String url="http://193.112.44.141:80/citi/merchant/getInfos";
        //RequestQueue queue= Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("json data===" + response.toString());
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name= jsonObject.getString("name");
                        String merchantID= jsonObject.getString("merchantID");
                        String logoURL= jsonObject.getString("logoURL");
                        String description= jsonObject.getString("description");
                        String address= jsonObject.getString("address");

                        System.out.println(name);
                        MerchantInfo.getInstance(PayingActivity.this).setMerchantID(merchantID)
                                .setName(name)
                                .setLogoURL(logoURL)
                                .setDescription(description)
                                .setAddress(address);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();

                map.put("start","0");
                map.put("n", "1");

                return map;
            }
        };

        //queue.add(request);
        //VolleySingleton.getVolleySingleton(this).addToRequestQueue(request);
        //设置请求标签用于加入全局队列后，方便找到
        request.setTag("postsr");
        //添加到全局的请求队列
        MyApplication.getHttpQueues().add(request);
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
        mAdapter.getFilter().filter(query);
        return true;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }


    //private static List<WordModel> filter(List<WordModel> models, String query) {
    //    final String lowerCaseQuery = query.toLowerCase();
    //
    //    final List<WordModel> filteredModelList = new ArrayList<>();
    //    for (WordModel model : models) {
    //        final String text = model.getWord().toLowerCase();
    //        final String rank = String.valueOf(model.getRank());
    //        if (text.contains(lowerCaseQuery) || rank.contains(lowerCaseQuery)) {
    //            filteredModelList.add(model);
    //        }
    //    }
    //    return filteredModelList;
    //}



}

