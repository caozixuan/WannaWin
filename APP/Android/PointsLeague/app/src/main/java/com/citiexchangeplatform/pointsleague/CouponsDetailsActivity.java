package com.citiexchangeplatform.pointsleague;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CouponsDetailsActivity extends AppCompatActivity {

    private String logoURL;
    //private String name;
    //兑换日期
    private String exchangeDate;
    //使用日期、有效期限
    private String validityDate;
    private String itemID;
    private String description;
    private String type;
    private String points;

    //控件
    private TextView TValidityDate;
    private TextView TExchangeDate;
    private TextView TDescription;
    private TextView TNeedPoints;
    private TextView TDateType;
    private TextView TStatus;
    //private TextView TName;
    //private TextView hint;
    private ImageView logo;
    private ImageView qrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle bundle = getIntent().getExtras();
        type = (String)bundle.get("logoURL");

        getData();
        if(type.equals("unused")){
            setContentView(R.layout.activity_coupons_details);
            initViewUnused();
        }

        else {
            setContentView(R.layout.activity_coupons_details_invalid);
            initViewElse();
        }
        toolBar();


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

        titleBar.setTitle("优惠券详情");
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

    public void initViewUnused(){
        TNeedPoints = findViewById(R.id.textView_coupon_details_points);
        logo = findViewById(R.id.imageView_coupons_details);
        //TName = findViewById(R.id.textView_coupon_business_name);
        TDescription = findViewById(R.id.textView_coupon_description);
        TValidityDate = findViewById(R.id.textView_validity_term);
        TExchangeDate = findViewById(R.id.textView_coupon_exchange_date);
        qrCode = findViewById(R.id.imageview_qr);



        createBitMap();

    }

    public void initViewElse(){
        TNeedPoints = findViewById(R.id.textView_coupon_details_points);
        logo = findViewById(R.id.imageView_coupons_details);
        //TName = findViewById(R.id.textView_coupon_business_name);
        TDescription = findViewById(R.id.textView_coupon_description);
        TValidityDate = findViewById(R.id.textView_validity_term);
        TDateType = findViewById(R.id.textview_date_type);
        TStatus = findViewById(R.id.textView_status);
        //TExchangeDate = findViewById(R.id.textView_coupon_exchange_date);
        //hint = findViewById(R.id.textView_hint);
        //hint.setText("");

    }


    public void getData(){
        Bundle bundle = getIntent().getExtras();
        //SerializableHashMap serializableHashMap = (SerializableHashMap) bundle.get("checkbox_map");
        //map = serializableHashMap.getMap();

        logoURL = (String)bundle.get("logoURL");
        points = (String)bundle.get("points");
        //name = (String)bundle.get("name");
        exchangeDate = (String)bundle.get("exchangeDate");
        validityDate = (String)bundle.get("validityDate");
        itemID = (String)bundle.get("itemID");
        description = (String)bundle.get("description");
        type = (String)bundle.get("type");

    }

    public void init(){

        Glide.with(CouponsDetailsActivity.this)
                .load(logoURL)
                .placeholder(R.drawable.ic_points_black_24dp)
                .error(R.drawable.ic_mall_black_24dp)
                .override(60,60)
                .into(logo);
        TNeedPoints.setText(points);
        TDescription.setText(description);

        TValidityDate.setText(validityDate);
        switch (type){
            case "unused":
                TExchangeDate.setText(exchangeDate);
                break;
            case "used":
                TDateType.setText("使用日期：");
                TStatus.setTextColor(Color.parseColor("#CDB03E"));
                break;
            case "overdue":
                TDateType.setText("有效期至：");
                TStatus.setText("已过期");
                TStatus.setTextColor(Color.parseColor("#CE512E"));;
                break;
        }


        //TName.setText(name);

    }


    public void createBitMap() {

        String content = "http://193.112.44.141:80/citi/userCoupon/use?userID="
                +LogStateInfo.getInstance(CouponsDetailsActivity.this).getUserID()+"&itemID="+itemID;

        System.out.println(content);

        //清空原有图片
        qrCode.setImageDrawable(null);

        /*生成二维码*/
        Bitmap bitmap_qr = ZXingUtils.createQRImage(content, 200,  200);
        qrCode.setImageBitmap(bitmap_qr);

    }
}
