package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.citiexchangeplatform.pointsleague.adapter.AllCardAdapter;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AllCardActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ProgressDialog dialog;
    AllCardAdapter allCardAdapter;
    int cardCount = 0;

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

        getCardCount();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_all_card);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("全部卡列表");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton_all_card);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToAddCard = new Intent(AllCardActivity.this, AddCardActivity.class);
                startActivity(intentToAddCard);
            }
        });
    }

    private void getInfos() {
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
                                allCardAdapter.addData(merchantID, name, merchantLogoURL, point, proportion);
                            }
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
                        dialog = ProgressDialog.show(AllCardActivity.this, "", "正在获取卡列表...");
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
                            cardCount = jsonObject.getInt("num");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();

                        getInfos();
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
                        dialog = ProgressDialog.show(AllCardActivity.this, "", "正在获取卡数量...");
                    }
                });
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
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        allCardAdapter.getFilter().filter(newText);
        return true;
    }
}
