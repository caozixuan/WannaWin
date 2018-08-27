package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.citiexchangeplatform.pointsleague.adapter.AllCardAdapter;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

public class AllCardActivity extends AppCompatActivity {

    ProgressDialog dialog;
    AllCardAdapter allCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_card);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_all_card);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置Adapter
        allCardAdapter = new AllCardAdapter(AllCardActivity.this);
        recyclerView.setAdapter(allCardAdapter);
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        TitleBar titleBar = (TitleBar) findViewById(R.id.toolbar_all_card);
        titleBar.setLeftImageResource(R.drawable.ic_left_orange_24dp);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextColor(0xFFFF9546);

        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleBar.setTitle("卡列表");
        titleBar.setTitleColor(Color.BLACK);

        //右侧
        titleBar.addAction(new TitleBar.ImageAction(R.drawable.ic_add_orange_24dp) {
            @Override
            public void performAction(View view) {
                Intent intentToAddCard = new Intent(AllCardActivity.this, AddCardActivity.class);
                startActivity(intentToAddCard);
            }
        });
        //沉浸式
        //titleBar.setImmersive(true);

        android.widget.SearchView search = this.findViewById(R.id.search_all_card);
        search.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                allCardAdapter.getFilter().filter(newText);
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        allCardAdapter.clearAll();
        getCardCount();
    }

    private void getInfos(int cardCount) {
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/mscard/infos")
                .addParam("userID", LogStateInfo.getInstance(AllCardActivity.this).getUserID())
                .addParam("n", String.valueOf(cardCount))
                .build()
                .execute(AllCardActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jobj = jsonArray.getJSONObject(i);
                                String name = jobj.getString("merchantName");
                                String merchantLogoURL = jobj.getString("merchantLogoURL");
                                int point = jobj.getInt("points");
                                double proportion = jobj.getDouble("proportion");
                                String merchantID = jobj.getString("merchantID");
                                int cardStyle = jobj.getInt("cardStyle");
                                allCardAdapter.addData(merchantID, name, merchantLogoURL, point, proportion, cardStyle);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        Toast.makeText(AllCardActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void getCardCount(){
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/mscard/getNum")
                .addParam("userID", LogStateInfo.getInstance(AllCardActivity.this).getUserID())
                .build()
                .execute(AllCardActivity.this, new CallBack<String>() {
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
                        Toast.makeText(AllCardActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBefore() {
                        super.onBefore();
                        dialog = ProgressDialog.show(AllCardActivity.this, "", "正在获取卡信息...");
                    }
                });
    }

}
