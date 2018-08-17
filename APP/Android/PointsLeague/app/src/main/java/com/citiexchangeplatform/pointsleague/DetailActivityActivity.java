package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivityActivity extends AppCompatActivity {

    ProgressDialog dialog;
    String activityID;
    ImageView imageViewLogo;
    TextView textViewName;
    TextView textViewDescription;
    TextView textViewTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activity);

        toolBar();

        imageViewLogo = (ImageView) findViewById(R.id.imageView_detail_activity);
        textViewName = (TextView) findViewById(R.id.textView_name_detail_activity);
        textViewDescription = (TextView) findViewById(R.id.textView_description_detail_activity);
        textViewTime = (TextView) findViewById(R.id.textView_time_detail_activity);


        Intent intent = getIntent();
        activityID = intent.getStringExtra("activityID");

        loadInfo(activityID);

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

        TitleBar titleBar = (TitleBar) findViewById(R.id.toolbar_detail_activity);
        titleBar.setLeftImageResource(R.drawable.ic_left_orange_24dp);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextColor(0xFFFF9546);

        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleBar.setTitle("活动详情");
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

    private void loadInfo(String activityID){
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/activity/getActivity")
                .addParam("activityID", activityID)
                .build()
                .execute(DetailActivityActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String name = jsonObject.getString("name");
                            String description = jsonObject.getString("description");
                            String imageURL = jsonObject.getString("imageURL");
                            String startDate = jsonObject.getString("startDate");
                            String endDate = jsonObject.getString("endDate");


                            Glide.with(DetailActivityActivity.this)
                                    .load(imageURL)
                                    .centerCrop()
                                    .error(R.drawable.loading_card)
                                    .into(imageViewLogo);
                            textViewName.setText(name);
                            textViewDescription.setText(description);
                            textViewTime.setText("活动有效期：" + startDate + " - " + endDate);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();

                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        dialog.dismiss();
                        Toast.makeText(DetailActivityActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBefore() {
                        super.onBefore();
                        dialog = ProgressDialog.show(DetailActivityActivity.this, "", "正在加载活动信息...");
                    }
                });
    }

}
