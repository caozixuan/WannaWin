package com.citiexchangeplatform.pointsleague;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PayCodeFinishActivity extends AppCompatActivity {


    private TextView usePoints;
    private TextView originalPrice;
    private TextView currentPrice;
    //private TextView name;

    private ImageView logo;


    private double usePoint;
    private double originalPrices;
    private double currentPrices;
    private String logoURL;
    private String merchantName;



    private double total;
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
        setContentView(R.layout.activity_pay_code_finish);
        toolBar();

        //设置toolbar
        //Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_paying_finish);
        //setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //广告栏
        imageIds = new int[]{R.drawable.ad1, R.drawable.ad2, R.drawable.ad3};

        imageDescriptions = new String[]{
                " ",
                " ",
                " "
        };
        //保存
        List<ImageView> imageList = new ArrayList<ImageView>();
        List<String> descList = new ArrayList<String>();


        Bundle bundle = getIntent().getExtras();
        //bundle.putDouble("originalPrice",originalPrice);
        //                        bundle.putDouble("priceAfter",priceAfter);
        //                        bundle.putDouble("pointsNeeded",pointsNeeded);

        originalPrices = (Double) bundle.get("originalPrice");
        currentPrices = (Double) bundle.get("priceAfter");
        usePoint = (Double) bundle.get("pointsNeeded");
        merchantName = (String) bundle.get("merchantName");
        logoURL = (String) bundle.get("merchantLogoURL");

        //初始化数据
        initView();
        initAdData();
        initEvent();


    }

    private void initView() {
        //商家logo
        logo = (ImageView)findViewById(R.id.imageView_scan_finish_business);
        //使用通用积分
        usePoints = (TextView)findViewById(R.id.textView_points_usedTotal);
        //原价
        originalPrice = (TextView)findViewById(R.id.textView_original_price);
        //当前价格
        currentPrice = (TextView)findViewById(R.id.textView_current_price);
        //商家名称
        //name = (TextView)findViewById(R.id.textView_merchant_name_paying_finish);


        //name.setText(merchantName);
        usePoints.setText(String.valueOf(usePoint));
        originalPrice.setText(String.valueOf(originalPrices));

        TextView tag = findViewById(R.id.textView_original_price_tag);
        //中划线
        tag.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        currentPrice.setText(String.valueOf(currentPrices));

        Glide.with(PayCodeFinishActivity.this)
                .load(logoURL)
                .placeholder(R.drawable.ic_points_black_24dp)
                .error(R.drawable.ic_mall_black_24dp)
                .override(60,60)
                .into(logo);

        //广告栏
        viewPager = (ViewPager) findViewById(R.id.vp_ad);
        imageDesc = (TextView) findViewById(R.id.id_image_desc);
        dotsGroup = (LinearLayout) findViewById(R.id.id_dots);
        imageDesc.setText(imageDescriptions[0]);
    }

    private void initAdData() {
        if (isFirstCreateView) {
            for (int i = 0; i < imageIds.length; i++) {
                //初始化图片资源
                ImageView imageView = new ImageView(this);
                imageView.setBackgroundResource(imageIds[i]);
                imageList.add(imageView);
                //初始化点
                ImageView point = new ImageView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                if (i == 0) {
                    isFirstCreateView = false;
                    point.setEnabled(true);
                    layoutParams.rightMargin = 30;
                    layoutParams.leftMargin = 30;
                    point.setLayoutParams(layoutParams);
                } else {
                    layoutParams.rightMargin = 30;
                    point.setLayoutParams(layoutParams);
                    point.setEnabled(false);
                }
                point.setBackgroundResource(R.drawable.point_bg);
                dotsGroup.addView(point);
            }
        }
    }

    private void initEvent() {
        viewPager.setAdapter(new AdAdapter(descList,imageList));
        //减去后面的数是为了保证一开始的界面是我们的第一张图片
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageList.size()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            /**
             * 页面正在滑动的时候，回调
             */
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            /**
             * 页面切换后调用
             * position  新的页面位置
             */
            public void onPageSelected(int position) {
                position = position % imageList.size();
                imageDesc.setText(imageDescriptions[position]);
                dotsGroup.getChildAt(lastPoint).setEnabled(false);
                dotsGroup.getChildAt(position).setEnabled(true);
                lastPoint = position;
            }

            @Override
            /**
             * 当页面状态发生变化的时候，回调
             */
            public void onPageScrollStateChanged(int state) {

            }
        });
        isRunning = true;
        handler.sendEmptyMessageDelayed(0, 2000);
    }

    /**
     * 判断是否自动滚动
     */
    private boolean isRunning = false;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            //让viewPager 滑动到下一页
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            if (isRunning) {
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        }
    };

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



        titleBar.setActionTextColor(getResources().getColor(R.color.colorLightOrange));
        //右侧
        titleBar.addAction(new TitleBar.TextAction("完成") {
            @Override
            public void performAction(View view) {
                finish();

            }
        });



        titleBar.setTitle("支付结果");
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





}
