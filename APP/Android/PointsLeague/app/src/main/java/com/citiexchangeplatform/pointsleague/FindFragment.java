package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.citiexchangeplatform.pointsleague.adapter.AllCardAdapter;
import com.citiexchangeplatform.pointsleague.adapter.FindActivityAdapter;
import com.citiexchangeplatform.pointsleague.adapter.FindAdapter;
import com.leochuan.CenterSnapHelper;
import com.leochuan.ScaleLayoutManager;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.build.PostFormBuilder;
import com.study.xuan.xvolleyutil.callback.CallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FindFragment extends Fragment {

    View view;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    FindAdapter findAdapter;
    FindActivityAdapter findActivityAdapter;

    ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find, null);

        initRecyclerView();

        initToolbar();

        initSearchView();

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        if(findAdapter.getItemCount() == 0){
            findActivityAdapter.clearAll();
            findAdapter.clearAll();
            getRecommendedActivity();
            getRecommendedMerchants();
        }

    }

    private void initRecyclerView(){
        recyclerView1 = (RecyclerView) view.findViewById(R.id.recyclerView_activity_find);
        recyclerView1.setLayoutManager(
                new ScaleLayoutManager
                        .Builder(getContext(), 2)
                        .setMinScale(1.0f)
                        .setOrientation(OrientationHelper.HORIZONTAL)
                        .build());
        new CenterSnapHelper().attachToRecyclerView(recyclerView1);
        findActivityAdapter = new FindActivityAdapter(getContext());
        recyclerView1.setAdapter(findActivityAdapter);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());

        recyclerView2 = (RecyclerView) view.findViewById(R.id.recyclerView_find);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView2.setLayoutManager(layoutManager2);
        findAdapter = new FindAdapter(getContext());
        recyclerView2.setAdapter(findAdapter);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
    }

    private void initToolbar(){
        TitleBar titleBar = (TitleBar) view.findViewById(R.id.toolbar_find);
        titleBar.setTitle("发现");
        titleBar.setTitleColor(Color.BLACK);
    }

    private void initSearchView(){
        //android.widget.SearchView search = view.findViewById(R.id.searchView_find);
        //
        //search.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Intent intentToSearch = new Intent(getContext(),SearchActivity.class);
        //        startActivity(intentToSearch);
        //
        //    }
        //});

        LinearLayout layout = view.findViewById(R.id.layout_search_find);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToSearch = new Intent(getContext(),SearchActivity.class);
                startActivity(intentToSearch);
            }
        });

        //search.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
        //    @Override
        //    public boolean onQueryTextSubmit(String query) {
        //        return false;
        //    }
        //
        //    @Override
        //    public boolean onQueryTextChange(String newText) {
        //        findAdapter.getFilter().filter(newText);
        //        return true;
        //    }
        //});

        //search.setIconified(false);
        //search.setIconifiedByDefault(false);
        //search.setSubmitButtonEnabled(false);
        //search.clearFocus();
        //search.clearFocus();
        //try {        //--拿到字节码
        //    Class<?> argClass = search.getClass();
        //    //--指定某个私有属性,mSearchPlate是搜索框父布局的名字
        //    Field ownField = argClass.getDeclaredField("mSearchPlate");
        //    //--暴力反射,只有暴力反射才能拿到私有属性
        //    ownField.setAccessible(true);
        //    View mView = (View) ownField.get(search);
        //    //--设置背景
        //    mView.setBackgroundColor(Color.TRANSPARENT);
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
    }

    private void getRecommendedActivity() {
        PostFormBuilder url =
                XVolley.getInstance()
                        .doPost()
                        .url("http://193.112.44.141:80/citi/recommend/getRecommendedItems");

        if (LogStateInfo.getInstance(getContext()).isLogin()) {
            url = url.addParam("userID", LogStateInfo.getInstance(getContext()).getUserID());
        }

        url.build().execute(getContext(), new CallBack<String>() {
            @Override
            public void onSuccess(Context context, String response) {
                System.out.println(response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        findActivityAdapter.addData(jsonObject.getString("ItemID"), jsonObject.getString("name"), jsonObject.getString("logoURL"));
                    }
                    recyclerView1.scrollToPosition(findActivityAdapter.getItemCount() / 2);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

//            @Override
//            public void onError(VolleyError error) {
//                super.onError(error);
//                Toast.makeText(getContext(), "服务器连接失败", Toast.LENGTH_LONG).show();
//            }
        });

    }

    private void getRecommendedMerchants(){
        String url="http://193.112.44.141:80/citi/recommend/getRecommendedMerchants";
        dialog = ProgressDialog.show(getContext(), "", "正在获取商家信息...");
        System.out.println("获取推荐商家");
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
                            case "operator":
                                businessType = "运营商";
                                break;
                            case "aviation":
                                businessType = "航空";
                                break;
                            case "hotel":
                                businessType = "酒店";
                                break;
                            case "supermarket":
                                businessType = "超市便利店";
                                break;
                            case "movie":
                                businessType = "电影";
                                break;
                            default:
                                businessType = "一般";
                        }
                        String merchantLogoURL = jsonObject.getString("merchantLogoURL");
                        findAdapter.addData(name, merchantID, merchantLogoURL, businessType, description);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println(volleyError.toString());
                System.out.println("获取推荐商家失败");
                Toast.makeText(getContext(),"获取推荐商家失败",Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();

//                if (LogStateInfo.getInstance(getContext()).isLogin()) {
//                    map.put("userID",LogStateInfo.getInstance(getContext()).getUserID());
//                }


                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    private void getRecommendedMerchants2() {
        PostFormBuilder url =
                XVolley.getInstance()
                        .doPost()
                        .url("http://193.112.44.141:80/citi/recommend/getRecommendedMerchants");

        if (LogStateInfo.getInstance(getContext()).isLogin()) {
            url = url.addParam("userID", LogStateInfo.getInstance(getContext()).getUserID());
        }

        url.build().execute(getContext(), new CallBack<String>() {
            @Override
            public void onSuccess(Context context, String response) {
                System.out.println(response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
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
                        findAdapter.addData(name, merchantID, merchantLogoURL, businessType, description);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

//            @Override
//            public void onError(VolleyError error) {
//                super.onError(error);
//                Toast.makeText(getContext(), "服务器连接失败", Toast.LENGTH_LONG).show();
//            }
        });

    }

}
