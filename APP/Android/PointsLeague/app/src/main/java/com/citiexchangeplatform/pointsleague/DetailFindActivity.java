package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.citiexchangeplatform.pointsleague.adapter.DetailFindAdapter;
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
    ListView listView;
    DetailFindAdapter detailFindAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_find);

        Intent intent = getIntent();
        merchantID = intent.getStringExtra("merchantID");

        imageViewLogo = (ImageView) findViewById(R.id.imageView_logo_detail_find);
        textViewName = (TextView) findViewById(R.id.textView_name_detail_find);
        textViewDescription = (TextView) findViewById(R.id.textView_description_detail_find);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail_find);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        finish();
        //    }
        //});

        toolBar();

        listView = (ListView) findViewById(R.id.listView_detail_find);
        listView.setEmptyView(findViewById(R.id.view_detail_find_empty));
        detailFindAdapter = new DetailFindAdapter(DetailFindActivity.this);
        listView.setAdapter(detailFindAdapter);

        getInfos();

        loadListItems();

    }

    public void toolBar(){
        boolean isImmersive = false;
        if (hasKitKat() && !hasLollipop()) {
            isImmersive = true;
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        } else if (hasLollipop()) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    //| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            isImmersive = true;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        final TitleBar titleBar = (TitleBar) findViewById(R.id.title_bar);
        titleBar.setDividerColor(Color.GRAY);
        titleBar.setLeftImageResource(R.drawable.ic_left_black_24dp);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextColor(Color.BLACK);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleBar.setTitle("发现页详情");
        titleBar.setTitleColor(Color.BLACK);

        //沉浸式
        titleBar.setImmersive(isImmersive);
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
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
                                String time = jsonObject.getString("overdueTime");
                                String description = jsonObject.getString("description");
                                String logoURL = jsonObject.getString("logoURL");
                                int points = jsonObject.getInt("points");
                                detailFindAdapter.addData(itemID, name, time, description, logoURL, points);
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
