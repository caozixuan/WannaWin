package com.citiexchangeplatform.pointsleague;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


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
    List<RecordParent> recordList;
    ExpandableAdapter adapter;
    VExpandableAdapter expandableAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_expandlist);
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);

        vertical(recyclerView);

        getPointsExchangeRecordRequest();

        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expandableAdapter.getRecordList().clear();
                getPointsExchangeRecordRequest();

                expandableAdapter.notifyDataSetChanged();
            }
        });

        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableAdapter.collapseAllGroup();
            }
        });
        findViewById(R.id.open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableAdapter.expandAllGroup();
            }
        });

    }


    private void vertical(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        expandableAdapter = new VExpandableAdapter(PointsExchangeExpandListActivity.this);
        recyclerView.setAdapter(expandableAdapter);


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
                            double exchangePoints = child.getInt("points_citi");
                            //保留两位小数
                            NumberFormat nf = NumberFormat.getNumberInstance();
                            // 保留两位小数
                            nf.setMaximumFractionDigits(2);
                            // 如果不需要四舍五入，可以使用RoundingMode.DOWN
                            nf.setRoundingMode(RoundingMode.UP);
                            String result = nf.format(exchangePoints);
                            recordChild.name = businessName;
                            recordChild.usePoints = "使用积分: " + String.valueOf(usePoints);
                            recordChild.exchangePoints = "兑换积分: " + result;
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
                        recordParent.totalExchangePoint = "总兑换积分" + result;
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
