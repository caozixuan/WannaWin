package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.citiexchangeplatform.pointsleague.adapter.AllCardAdapter;
import com.citiexchangeplatform.pointsleague.adapter.FindAdapter;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

public class FindFragment extends Fragment{

    View view;
    RecyclerView recyclerView;
    FindAdapter findAdapter;

    ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find, null);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView_find);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        findAdapter = new FindAdapter(getContext());
        recyclerView.setAdapter(findAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        android.widget.SearchView search = view.findViewById(R.id.searchView_find);

        search.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                findAdapter.getFilter().filter(newText);
                return true;
            }
        });
        search.setIconified(false);
        search.setQueryHint("请输入商家名");
        //设置搜索框展开时是否显示提交按钮，可不显示
        search.setSubmitButtonEnabled(false);
        //让键盘的回车键设置成搜索
        search.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        search.clearFocus();


        getCount();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void getCount(){
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/merchant/getNum")
                .build()
                .execute(getContext(), new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            getInfos(jsonObject.getInt("num"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        dialog.dismiss();
                        Toast.makeText(getContext(), "服务器连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBefore() {
                        super.onBefore();
                        dialog = ProgressDialog.show(getContext(), "", "正在获取商家信息...");
                    }
                });
    }

    private void getInfos(int n) {
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/merchant/getInfos")
                .addParam("start", "0")
                .addParam("n", "20")
                .build()
                .execute(getContext(), new CallBack<String>() {
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
                                switch (jsonObject.getString("businessType")){
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

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        Toast.makeText(getContext(), "服务器连接失败", Toast.LENGTH_LONG).show();
                    }
                });
    }

}
