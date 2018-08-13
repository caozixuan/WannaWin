package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.citiexchangeplatform.pointsleague.adapter.DetailFindAdapter;
import com.citiexchangeplatform.pointsleague.adapter.FindActivityAdapter;
import com.citiexchangeplatform.pointsleague.adapter.FindAdapter;
import com.citiexchangeplatform.pointsleague.models.DetailFindItemModel;
import com.leochuan.CenterSnapHelper;
import com.leochuan.ScaleLayoutManager;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailFindActivity extends AppCompatActivity {

    String merchantID;

    ProgressDialog dialog;

    ImageView imageViewLogo;
    TextView textViewName;
    TextView textViewDescription;

    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    DetailFindAdapter detailFindAdapter;
    FindActivityAdapter findActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_find);

        Intent intent = getIntent();
        merchantID = intent.getStringExtra("merchantID");

        imageViewLogo = (ImageView) findViewById(R.id.imageView_logo_detail_find);
        textViewName = (TextView) findViewById(R.id.textView_name_detail_find);
        textViewDescription = (TextView) findViewById(R.id.textView_description_detail_find);

        toolBar();

        recyclerView2 = (RecyclerView)findViewById(R.id.recyclerView_detail_find);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(DetailFindActivity.this);
        recyclerView2.setLayoutManager(layoutManager2);
        detailFindAdapter = new DetailFindAdapter(DetailFindActivity.this);
        recyclerView2.setAdapter(detailFindAdapter);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        recyclerView1 = (RecyclerView)findViewById(R.id.recyclerView_ad_detail_find);
        recyclerView1.setLayoutManager(
                new ScaleLayoutManager
                        .Builder(DetailFindActivity.this,2)
                        .setMinScale(1.0f)
                        .setOrientation(OrientationHelper. HORIZONTAL)
                        .build());
        new CenterSnapHelper().attachToRecyclerView(recyclerView1);
        findActivityAdapter = new FindActivityAdapter(DetailFindActivity.this);
        recyclerView1.setAdapter(findActivityAdapter);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());

        findActivityAdapter.addData("123123","!23","123","123","123");
        findActivityAdapter.addData("123123","!23","123","123","123");
        findActivityAdapter.addData("123123","!23","123","123","123");
        recyclerView1.scrollToPosition(findActivityAdapter.getItemCount()/2);

        getInfos();

        loadListItems();

    }

    public void toolBar(){
        TitleBar titleBar = (TitleBar) findViewById(R.id.toolbar_detail_find);
        titleBar.setLeftImageResource(R.drawable.ic_left_orange_24dp);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextColor(0xFFFF9546);

        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleBar.setTitle("商户详情");
        titleBar.setTitleColor(Color.BLACK);
    }

    private void getInfos() {
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/merchant/getSingleInfo")
                .addParam("merchantID", merchantID)
                .build()
                .execute(DetailFindActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String name = jsonObject.getString("name");
                            String description = jsonObject.getString("description");
                            String merchantLogoURL = jsonObject.getString("merchantLogoURL");


                            Glide.with(DetailFindActivity.this)
                                    .load(merchantLogoURL)
                                    .placeholder(R.drawable.ic_points_black_24dp)
                                    .error(R.drawable.ic_points_black_24dp)
                                    .into(imageViewLogo);
                            textViewName.setText(String.valueOf(name));
                            textViewDescription.setText(description);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        dialog.dismiss();
                        Toast.makeText(DetailFindActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBefore() {
                        super.onBefore();
                        dialog = ProgressDialog.show(DetailFindActivity.this, "", "正在获取商家信息...");
                    }
                });
    }

    private void loadListItems(){
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/item/getMerchantItems")
                .addParam("merchantID", merchantID)
                .addParam("start", "0")
                .addParam("n", "40")
                .build()
                .execute(DetailFindActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String itemID = jsonObject.getString("ItemID");
                                String name = jsonObject.getString("name");
                                String description = jsonObject.getString("description");
                                int points = jsonObject.getInt("points");
                                detailFindAdapter.addData(new DetailFindItemModel(itemID, name, description, points));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        Toast.makeText(DetailFindActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
