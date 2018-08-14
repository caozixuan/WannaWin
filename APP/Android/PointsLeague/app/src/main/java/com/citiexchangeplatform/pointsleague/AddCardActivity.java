package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.citiexchangeplatform.pointsleague.adapter.AddCardAdapter;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

public class AddCardActivity extends AppCompatActivity{

    ProgressDialog dialog;
    AddCardAdapter addCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_add_card);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置Adapter
        addCardAdapter = new AddCardAdapter(AddCardActivity.this);
        recyclerView.setAdapter(addCardAdapter);
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        TitleBar titleBar = (TitleBar) findViewById(R.id.toolbar_add_card);
        titleBar.setLeftImageResource(R.drawable.ic_left_orange_24dp);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextColor(0xFFFF9546);

        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleBar.setTitle("添加会员卡");
        titleBar.setTitleColor(Color.BLACK);


        android.widget.SearchView search = this.findViewById(R.id.search_add_card);
        search.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                addCardAdapter.getFilter().filter(newText);
                return true;
            }
        });
        search.setIconified(false);
        search.setQueryHint("搜索");
        //搜索图标是否显示在搜索框内
        search.setIconifiedByDefault(false);
        //设置搜索框展开时是否显示提交按钮，可不显示
        search.setSubmitButtonEnabled(false);
        search.clearFocus();
        try {        //--拿到字节码
            Class<?> argClass = search.getClass();
            //--指定某个私有属性,mSearchPlate是搜索框父布局的名字
            Field ownField = argClass.getDeclaredField("mSearchPlate");
            //--暴力反射,只有暴力反射才能拿到私有属性
            ownField.setAccessible(true);
            View mView = (View) ownField.get(search);
            //--设置背景
            mView.setBackgroundColor(Color.TRANSPARENT);
        } catch (Exception e) {
            e.printStackTrace();
        }


        getCount();
    }

    private void getCount(){
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/merchant/getNum")
                .build()
                .execute(AddCardActivity.this, new CallBack<String>() {
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
                        Toast.makeText(AddCardActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBefore() {
                        super.onBefore();
                        dialog = ProgressDialog.show(AddCardActivity.this, "", "正在获取商家信息...");
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
                .execute(AddCardActivity.this, new CallBack<String>() {
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
                                String merchantLogoURL = jsonObject.getString("merchantLogoURL");
                                addCardAdapter.addData(merchantID, name, description, merchantLogoURL);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        Toast.makeText(AddCardActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }
                });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        final MenuItem searchItem = menu.findItem(R.id.action_search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        searchView.setOnQueryTextListener(this);
//
//        return true;
//    }
//
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        addCardAdapter.getFilter().filter(newText);
//        return true;
//    }

}
