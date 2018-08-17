package com.citiexchangeplatform.pointsleague;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.util.Preconditions;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class OrderDetailActivity extends AppCompatActivity {

    private String logoURL;
    private String name;
    private String time;
    private double usePoints;
    private double priceBefore;
    private double priceAfter;

    //控件
    private TextView TUsePoints;
    private TextView TPriceBefore;
    private TextView TPriceAfter;
    private TextView TOrderTime;

    private TextView TName;

    private ImageView logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        //设置toolBar
        toolBar();
        //findView
        initView();

        getData();

        init();


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

        titleBar.setLeftImageResource(R.drawable.ic_left_orange_24dp);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextSize(18);
        titleBar.setLeftTextColor(getResources().getColor(R.color.colorLightOrange));
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleBar.setTitle("详情");
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

    public void initView(){

        TName = findViewById(R.id.textView_order_business_name);
        logo = findViewById(R.id.imageView_order_logo);
        TUsePoints = findViewById(R.id.textView_order_userPoints);
        TPriceBefore = findViewById(R.id.textView_order_price_before);
        TPriceAfter = findViewById(R.id.textView_order_price_after);
        TOrderTime = findViewById(R.id.textView_order_time);
    }




    @SuppressLint("RestrictedApi")
    public void getData(){
        Bundle bundle = getIntent().getExtras();
        Preconditions.checkArgument(bundle != null);
        //SerializableHashMap serializableHashMap = (SerializableHashMap) bundle.get("checkbox_map");
        //map = serializableHashMap.getMap();

        //bundle.putString("logoURL",logoURL);
        //                bundle.putString("name",name);
        //                bundle.putDouble("pointsNeeded",pointsNeeded);
        //                bundle.putDouble("originalPrice",originalPrice);
        //                bundle.putDouble("priceAfter",priceAfter);

        logoURL = (String)bundle.get("logoURL");
        name = (String)bundle.get("name");
        time = (String)bundle.get("time");
        usePoints = (double) bundle.get("pointsNeeded");
        priceBefore = (double) bundle.get("originalPrice");
        priceAfter = (double) bundle.get("priceAfter");


    }

    public void init(){

        Glide.with(OrderDetailActivity.this)
                .load(logoURL)
                .placeholder(R.drawable.ic_points_black_24dp)
                .error(R.drawable.ic_mall_black_24dp)
                .override(60,60)
                .into(logo);

        TName.setText(name);
        TOrderTime.setText(time);
        TUsePoints.setText(String.valueOf(usePoints));
        TPriceBefore.setText(String.valueOf(priceBefore));
        TPriceAfter.setText(String.valueOf(priceAfter));

    }

}


