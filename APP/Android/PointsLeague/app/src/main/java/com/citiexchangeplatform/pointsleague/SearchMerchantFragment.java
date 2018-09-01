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

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.citiexchangeplatform.pointsleague.adapter.FindSearchAdapter;
import com.citiexchangeplatform.pointsleague.models.SearchKeyword;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchMerchantFragment extends Fragment{

    RecyclerView searchRecyclerView;
    private FindSearchAdapter searchAdapter;
    ProgressDialog dialog;
    String keyword;



    public SearchMerchantFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new SearchMerchantFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_merchant, container, false);
        searchRecyclerView = view.findViewById(R.id.recyclerView_search_merchant);

        //注册EventBus,接受关键字
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        //设置RecyclerView管理器
        setRecyclerView();

        //获得初始化数据
        initData();

        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(SearchKeyword event) {
        keyword = event.getKeyWord();
        System.out.println(keyword);
        getSearchTotalNum(keyword);
        getSearchMerchants();

    }

    protected void initData()
    {


        //getHistoryOrderByQRCode();

    }

    protected void setRecyclerView(){

        //// adapter是你自己为RecyclerView写的Adapter
        //RecyclerView.Adapter adapter = new YourOwnAdapter();
        //AdapterWrapper adapterWrapper = new AdapterWrapper(adapter);
        //RecyclerView recyclerView = findViewById();
        //// 将RecyclerView和刚创建的adapterWrapper传入
        //SwipeToLoadHelper helper = new SwipeToLoadHelper(recyclerView, adapterWrapper);

        searchAdapter = new FindSearchAdapter(getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        searchRecyclerView.setLayoutManager(layoutManager);
        searchRecyclerView.setAdapter(searchAdapter);

    }

    private void getSearchTotalNum(final String keyword){
        String url="http://193.112.44.141:80/citi/merchant/getNum";
        RequestQueue queue = MyApplication.getHttpQueues();
        final StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Log.e("success",s);
                System.out.println(s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    int num = jsonObject.getInt("num");

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

                map.put("keyword",keyword);

                return map;
            }
        };
        queue.add(request);
    }

    private void getSearchMerchants(){
        searchAdapter.clearAll();
        String url="http://193.112.44.141:80/citi/merchant/search";
        dialog = ProgressDialog.show(getContext(), "", "正在获取商家信息...");
        RequestQueue queue = MyApplication.getHttpQueues();
        final StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Log.e("success",s);
                System.out.println(s);
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String merchantID = jsonObject.getString("merchantID");
                        String name = jsonObject.getString("name");
                        String description = jsonObject.getString("description");
                        String businessType;
                        switch (jsonObject.getString("businessType")) {
                            case "catering":
                                businessType = "餐饮";
                                break;
                            case "exercise":
                                businessType = "运动";
                                break;
                            case "bank":
                                businessType = "银行";
                                break;
                            case "costume":
                                businessType = "服饰";
                                break;
                            case "education":
                                businessType = "教育";
                                break;
                            case "communication":
                                businessType = "通讯";
                                break;
                            default:
                                businessType = "一般";
                        }
                        String merchantLogoURL = jsonObject.getString("merchantLogoURL");

                        searchAdapter.addData(name, merchantID, merchantLogoURL, businessType, description);
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

                map.put("keyword",keyword);
                map.put("start","0");
                map.put("end","1");


                return map;
            }
        };

        queue.add(request);
    }

    @Override
    public void onResume() {
        super.onResume();


        getSearchTotalNum(keyword);
        getSearchMerchants();
        //getHistoryOrderByQRCode();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


}
