package com.citiexchangeplatform.pointsleague;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrderFragment extends Fragment {

    private EmptyRecyclerView orderRecyclerView;
    private View mEmptyView;
    //RecyclerView orderRecyclerView;
    private MyOrderAdapter orderAdapter;
    ProgressDialog dialog;


    public MyOrderFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new MyOrderFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_order, container, false);

        orderRecyclerView = view.findViewById(R.id.recyclerView_myOrder_overdue);
        mEmptyView = view.findViewById(R.id.empty_iv);

        //设置RecyclerView管理器
        setRecyclerView();

        //获得初始化数据
        initData();

        return view;

    }

    protected void initData()
    {
        //orderAdapter.addData("niki","5元代金券","2018-7-23");
        //orderAdapter.addData("中国联通","5元代金券","2018-7-26");
        //orderAdapter.addData("中国移动","5元代金券","2018-7-25");
        //orderAdapter.addData("中国电信","5元代金券","2018-7-27");
        //orderAdapter.addData("niki","5元代金券","2018-7-25");
        //orderAdapter.addData("中国联通","5元代金券","2018-7-27");

        dialog = ProgressDialog.show(getContext(), "", "正在加载订单信息...");
        getHistoryOrderByQRCode();




    }

    protected void setRecyclerView(){

        orderAdapter = new MyOrderAdapter(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderRecyclerView.setLayoutManager(layoutManager);
        orderRecyclerView.setAdapter(orderAdapter);
        orderRecyclerView.setEmptyView(mEmptyView); //设置空布局
    }


    private void getHistoryOrderByQRCode() {
        String url="http://193.112.44.141:80/citi/order/getOrders";
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


                        Double pointsNeeded = jObj.getDouble("pointsNeeded");
                        Double originalPrice = jObj.getDouble("originalPrice");
                        Double priceAfter = jObj.getDouble("priceAfter");
                        String merchantName = jObj.getString("merchantName");
                        //String logoURL = jObj.getString("logoURL");
                        String logoURL = "http://www.byzhong.cn/image/merchant/8aima_logo.png";
                        String time = jObj.getString("time");

                        orderAdapter.addData(merchantName,originalPrice,priceAfter,pointsNeeded,logoURL,time);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                dialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();

                map.put("userID",LogStateInfo.getInstance(getContext()).getUserID());
                map.put("intervalTime","1101010101");


                return map;
            }
        };
        queue.add(request);


    }

    @Override
    public void onResume() {
        super.onResume();
        getHistoryOrderByQRCode();
    }

}
