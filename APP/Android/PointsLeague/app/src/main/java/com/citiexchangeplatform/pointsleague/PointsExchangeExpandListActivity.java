package com.citiexchangeplatform.pointsleague;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.citiexchangeplatform.pointsleague.adapter.ExpandableAdapter;
import com.citiexchangeplatform.pointsleague.adapter.VExpandableAdapter;
import com.citiexchangeplatform.pointsleague.data.RecordChild;
import com.citiexchangeplatform.pointsleague.data.RecordParent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.citiexchangeplatform.pointsleague.utils.Utils.showJson;


public class PointsExchangeExpandListActivity extends AppCompatActivity {

    private View mEmptyView;
    List<RecordParent> recordList;
    ExpandableAdapter adapter;
    VExpandableAdapter expandableAdapter;
    ToggleButton toggleButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_expandlist);

        final EmptyRecyclerView recyclerView = findViewById(R.id.recyclerView);
        //final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mEmptyView = findViewById(R.id.empty_iv);
        toolBar();

        vertical(recyclerView);


        getPointsExchangeRecordRequest();


        toggleButton = (ToggleButton)findViewById(R.id.togglebutton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    expandableAdapter.expandAllGroup();
                }else{
                    expandableAdapter.collapseAllGroup();
                }
            }
        });

        //refresh
        //findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //
        //        expandableAdapter.getRecordList().clear();
        //        getPointsExchangeRecordRequest();
        //
        //        expandableAdapter.notifyDataSetChanged();
        //    }
        //});



        //调用方法,传入一个接口回调
        expandableAdapter.setItemClickListener(new VExpandableAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(PointsExchangeExpandListActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
                if (expandableAdapter.isExpanded(position)) {
                    expandableAdapter.collapseGroup(position);
                    //((Button) v).setText("Open");
                } else {
                    expandableAdapter.expandGroup(position);
                    //((Button) v).setText("Close");
                }
            }
        });
    }


    private void vertical(EmptyRecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        expandableAdapter = new VExpandableAdapter(PointsExchangeExpandListActivity.this);
        recyclerView.setAdapter(expandableAdapter);
        recyclerView.setEmptyView(mEmptyView); //设置空布局
        expandableAdapter.collapseAllGroup();


    }

    public void toolBar(){
        boolean isImmersive = false;
        if (hasKitKat() && !hasLollipop()) {
            isImmersive = true;
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        } else if (hasLollipop()) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    //| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            isImmersive = true;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        final TitleBar titleBar = (TitleBar) findViewById(R.id.title_bar);
        //titleBar.setDividerColor(Color.GRAY);
        titleBar.setLeftImageResource(R.drawable.ic_left_orange_24dp);
        titleBar.setBackgroundColor(Color.WHITE);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextColor(getResources().getColor(R.color.colorLightOrange));
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleBar.setTitle("积分兑换记录");
        titleBar.setTitleColor(Color.BLACK);

        //titleBar.setActionTextColor(Color.BLACK);
        //
        //
        //titleBar.addAction(new TitleBar.TextAction("发布") {
        //    @Override
        //    public void performAction(View view) {
        //        Toast.makeText(PayCodeActivity.this, "点击了发布", Toast.LENGTH_SHORT).show();
        //    }
        //});

        //沉浸式
        titleBar.setImmersive(isImmersive);
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    private void getPointsExchangeRecordRequest() {
        recordList = new ArrayList<>();
        String url="http://193.112.44.141:80/citi/points/getPointsHistoryByID";
        RequestQueue queue = MyApplication.getHttpQueues();
        //RequestQueue queue=Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //[{"merchantID":"4","points_card":295,"points_citi":0.0,"time":"Jul 14, 2018 2:41:44 PM"}]
                Log.e("success",s);
                System.out.println(s);
                try {
                    JSONArray jsonArray = new JSONArray(s);



                    for (int i = 0; i < jsonArray.length(); i++) {
                        List<RecordChild> recordChildList = new ArrayList<>();
                        String date = "";
                        JSONObject jObj = jsonArray.getJSONObject(i);
                        JSONArray children = jObj.getJSONArray("points_history_merchants");

                        for (int j = 0; j < children.length(); j++) {

                            JSONObject child = children.getJSONObject(j);
                            RecordChild recordChild = new RecordChild();
                            String businessName = child.getString("merchantName");
                            int usePoints = child.getInt("points_card");
                            double exchangePoints = child.getDouble("points_citi");

                            //保留两位小数
                            NumberFormat nf = NumberFormat.getNumberInstance();
                            // 保留两位小数
                            nf.setMaximumFractionDigits(2);
                            // 如果不需要四舍五入，可以使用RoundingMode.DOWN
                            nf.setRoundingMode(RoundingMode.UP);
                            String result = nf.format(exchangePoints);

                            recordChild.name = businessName;
                            recordChild.usePoints = String.valueOf(usePoints);
                            recordChild.exchangePoints = result;
                            date = String.valueOf(child.getString("time"));
                            recordChildList.add(recordChild);

                        }
                        RecordParent recordParent = new RecordParent();
                        Double total = jObj.getDouble("totalPoints");
                        //保留两位小数
                        NumberFormat nf = NumberFormat.getNumberInstance();
                        // 保留两位小数
                        nf.setMaximumFractionDigits(2);
                        // 如果不需要四舍五入，可以使用RoundingMode.DOWN
                        nf.setRoundingMode(RoundingMode.UP);
                        String result = nf.format(total);
                        recordParent.totalExchangePoint = result;
                        recordParent.date = date;
                        recordParent.childs = recordChildList;
                        expandableAdapter.addData(recordParent);


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
                map.put("userID",LogStateInfo.getInstance(PointsExchangeExpandListActivity.this).getUserID());

                return map;
            }
        };
        queue.add(request);


    }


}
