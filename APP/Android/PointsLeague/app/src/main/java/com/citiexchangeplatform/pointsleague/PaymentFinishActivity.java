package com.citiexchangeplatform.pointsleague;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaymentFinishActivity extends AppCompatActivity {

    private View view;
    private RecyclerView mRecyclerView;
    private List<String> data_posses_point;
    private List<Integer> business_image;
    private PayingFinishAdapter mAdapter;
    private TextView Text_NeedPoints;
    private ImageView ImageView_Business;
    private HashMap<Integer, Boolean> map = new HashMap<>();

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



        imageIds = new int[]{R.drawable.ad1, R.drawable.ad2, R.drawable.ad1};

        imageDescriptions = new String[]{
                "巩俐不低俗，我就不能低俗",
                "扑树又回来啦！再唱经典老歌引万人大合唱",
                "揭秘北京电影如何升级",
                "乐视网TV版大派送"
        };
        //保存
        List<ImageView> imageList = new ArrayList<ImageView>();
        List<String> descList = new ArrayList<String>();

        //通过findViewById拿到RecyclerView实例
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_finish_points);
        Text_NeedPoints = (TextView)findViewById(R.id.textView_points_usedTotal);

        ImageView_Business = (ImageView)findViewById(R.id.imageView_finish_business);


        Bundle bundle = getIntent().getExtras();
        //SerializableHashMap serializableHashMap = (SerializableHashMap) bundle.get("checkbox_map");
        //map = serializableHashMap.getMap();
        data_posses_point = (List) bundle.get("points_result");
        business_image = (List) bundle.get("image_resource");







        //初始化数据
        initData();
        initView();
        initAdData();
        initEvent();

        //设置RecyclerView管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PayingFinishAdapter(data_posses_point,business_image,getApplicationContext());

        mRecyclerView.setAdapter(mAdapter);




    }

    /*获得各项积分数据：商家图标、积分数*/
    protected void initData()
    {
        //设置需要的积分数
        Text_NeedPoints.setText("120p");

        ImageView_Business.setImageResource(R.drawable.nike_store);

    }

    private void initView() {
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


}
