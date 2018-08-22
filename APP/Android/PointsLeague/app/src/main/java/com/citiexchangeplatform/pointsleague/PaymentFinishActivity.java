package com.citiexchangeplatform.pointsleague;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaymentFinishActivity extends AppCompatActivity {

    private View view;
    private RecyclerView mRecyclerView;
    private List<String> points_used;
    private List<String> points_exchanged;
    private List<String> reasons;
    private List<String> names;
    private List<String> logos;

    private PayingFinishAdapter mAdapter;
    private TextView Text_NeedPoints;
    private ImageView ImageView_Business;

    private String total;
    private  Boolean state = true;

    //控件的声明
    private ViewPager viewPager;
    private TextView imageDesc;
    private LinearLayout dotsGroup;
    //数据声明
    //图片资源的ID
    private int[] imageIds;
    //用来保存上个视图的位置
    private int lastPoint;

    //为了不让Fragment每次在调用oncreteView都创建视图，造成视图的重复，定义一个布尔类型来确定是不是第一次调用
    private boolean isFirstCreateView = true;
    //图片标题集合
    private String[] imageDescriptions;

    //保存
    List<ImageView> imageList = new ArrayList<ImageView>();
    List<String> descList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_finish);

        //设置toolbar
        //Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_paying_finish);
        //setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolBar();




        //通过findViewById拿到RecyclerView实例
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_finish_points);
        Text_NeedPoints = (TextView)findViewById(R.id.textView_points_usedTotal);

        ImageView_Business = (ImageView)findViewById(R.id.imageView_finish_business);


        Bundle bundle = getIntent().getExtras();
        //SerializableHashMap serializableHashMap = (SerializableHashMap) bundle.get("checkbox_map");
        //map = serializableHashMap.getMap();

        state = (Boolean) bundle.get("state");
        logos = (List) bundle.get("logo_urls");
        names = (List) bundle.get("business_names");
        if(state){
            points_used = (List) bundle.get("points_used");
            points_exchanged = (List) bundle.get("points_exchanged");
            total = (String) bundle.get("total");

            Text_NeedPoints.setText(total);
        }
        else {
            reasons = (List) bundle.get("reasons");
        }

        //设置RecyclerView管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PayingFinishAdapter(state,names,logos,points_used,points_exchanged,reasons,getApplicationContext());

        mRecyclerView.setAdapter(mAdapter);


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
        //titleBar.setDividerColor(Color.GRAY);
        //左侧
        //titleBar.setLeftImageResource(R.drawable.ic_left_black_24dp);
        //titleBar.setLeftText("返回");
        //titleBar.setLeftTextColor(Color.BLACK);
        //titleBar.setLeftClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        finish();
        //    }
        //});

        titleBar.setTitle("兑换结果");
        titleBar.setTitleColor(Color.BLACK);


        titleBar.setActionTextColor(Color.BLACK);

        titleBar.setActionTextColor(getResources().getColor(R.color.colorLightOrange));
        //右侧
        titleBar.addAction(new TitleBar.TextAction("完成") {
            @Override
            public void performAction(View view) {
                finish();
            }
        });

        //沉浸式
        titleBar.setImmersive(isImmersive);
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }



}
