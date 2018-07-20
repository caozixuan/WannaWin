package com.citiexchangeplatform.pointsleague;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class OrderTabUnusedFragment extends Fragment {
    private RecyclerView unusedOrderRecyclerView;
    private MyOrderAdapter orderAdapter;


    public OrderTabUnusedFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        OrderTabUnusedFragment fragment = new OrderTabUnusedFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_tab_unused, container, false);
        unusedOrderRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_myOrder_unused);

        //设置RecyclerView管理器
        setRecyclerView();

        //获得初始化数据
        initData();

        return view;
    }

    /*订单：totalPoints,description,date*/
    protected void initData()
    {
        //orderAdapter.addData("niki","5元代金券","2018-7-23");
        //orderAdapter.addData("中国联通","5元代金券","2018-7-26");
        //orderAdapter.addData("中国移动","5元代金券","2018-7-25");
        //orderAdapter.addData("中国电信","5元代金券","2018-7-27");
        //orderAdapter.addData("niki","5元代金券","2018-7-25");
        //orderAdapter.addData("中国联通","5元代金券","2018-7-27");

        //getHistoryOrderByQRCode();

        getHistoryOrderByCoupon();


    }

    protected void setRecyclerView(){
        orderAdapter = new MyOrderAdapter(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        unusedOrderRecyclerView.setLayoutManager(layoutManager);
        orderAdapter = new MyOrderAdapter(getActivity());
        unusedOrderRecyclerView.setAdapter(orderAdapter);
    }


    //private void getHistoryOrderByQRCode() {
    //    String url="http://193.112.44.141:80/citi/order/getOrders";
    //    RequestQueue queue = MyApplication.getHttpQueues();
    //    //RequestQueue queue=Volley.newRequestQueue(this);
    //    StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
    //        @Override
    //        public void onResponse(String s) {
    //
    //            Log.e("success",s);
    //            System.out.println(s);
    //            try {
    //                JSONArray jsonArray = new JSONArray(s);
    //
    //                for (int i = 0; i < jsonArray.length(); i++) {
    //                    JSONObject jObj = jsonArray.getJSONObject(i);
    //
    //
    //                    String pointsNeeded = "使用花旗点：" + jObj.getString("pointsNeeded");
    //                    String merchantName = jObj.getString("merchantName");
    //                    String time = jObj.getString("time");
    //
    //                    orderAdapter.addData(merchantName,pointsNeeded,time);
    //
    //                }
    //
    //
    //            } catch (JSONException e) {
    //                e.printStackTrace();
    //            }
    //
    //        }
    //    }, new Response.ErrorListener() {
    //        @Override
    //        public void onErrorResponse(VolleyError volleyError) {
    //
    //        }
    //    }){
    //        @Override
    //        protected Map<String, String> getParams() throws AuthFailureError {
    //            Map<String,String> map=new HashMap<>();
    //
    //            map.put("userID",LogStateInfo.getInstance(getContext()).getUserID());
    //            map.put("intervalTime","1101010101");
    //
    //
    //            return map;
    //        }
    //    };
    //    queue.add(request);
    //
    //
    //}
    private void getHistoryOrderByCoupon() {
        String url="http://193.112.44.141:80/citi/userCoupon/getUnusedCoupons";
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

                        String description = jObj.getString("description");
                        String itemName = jObj.getString("itemName");
                        String time = "兑换日期："+jObj.getString("getTime");
                        String validityTerm = "有效期："+jObj.getString("overdueTime");
                        //String validityTerm = jObj.getString("overdueTime");
                        String logoURL  = "http://www.never-give-it-up.top/wp-content/uploads/2018/07/zhouheiya_logo.png";
                        String itemID = jObj.getString("ItemID");

                        orderAdapter.addData(itemName,description,time,validityTerm,logoURL,itemID,"unused");

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

                map.put("userID",LogStateInfo.getInstance(getContext()).getUserID());

                return map;
            }
        };
        queue.add(request);


    }

}
