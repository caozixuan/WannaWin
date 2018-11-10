package com.citiexchangeplatform.pointsleague;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class PayCodeActivity extends AppCompatActivity {
    int times = (int)(System.currentTimeMillis() / 1000);
    private long count = 0;
    private boolean run = false;
    ImageView iv_bar;
    ImageView iv_qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_code);

        //设置toolbar
        //Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_pay_code);
        //setSupportActionBar(mToolbar);
        //getSupportActionBar().setTitle("");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolBar();

        iv_bar = (ImageView)findViewById(R.id.imageview_barcode);
        iv_qr = (ImageView)findViewById(R.id.imageview_qr);


        times = (int)(System.currentTimeMillis() / 1000);
        String content = "{\"userID\":"+ "\""+ LogStateInfo.getInstance(PayCodeActivity.this).getUserID() +"\""+ ",\"timeStamp\":"+ "\""+ times +"\"}";
        createBitMap(content);
        System.out.println(content);

        //handler.post(task);
        run = true;
        handler.postDelayed(task, 1000);


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
        titleBar.setLeftImageResource(R.drawable.ic_left_black_24dp);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextColor(Color.BLACK);
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run = false;
                finish();
            }
        });

        titleBar.setTitle("积分码");
        titleBar.setTitleColor(Color.BLACK);

        //titleBar.setActionTextColor(Color.BLACK);
        //
        //
        //titleBar.addAction(new TitleBar.TextAction("发布") {
        //    @Override
        //    public void performAction(View view) {
        //        Toast.makeText(PayCodeActivity.this, "点击了发布", Toast.LENGTH_SHORT).show();
        //    }
        //});

        //沉浸式
        titleBar.setImmersive(isImmersive);
    }

    public void createBitMap(String content) {

        //清空原有图片
        iv_bar.setImageDrawable(null);
        iv_qr.setImageDrawable(null);
        /*生成二维码、条形码*/
        /*生成条形码*/

        //Bitmap bitmap_bar = ZXingUtils.creatBarcode(getApplicationContext(), content, iv_bar.getWidth(),  iv_bar.getHeight(),true);
        //iv_bar.setImageBitmap(bitmap_bar);

        Bitmap bitmap_bar = ZXingUtils.creatBarcode(getApplicationContext(), content,450,  200,false);
        iv_bar.setImageBitmap(bitmap_bar);
        /*生成二维码*/
        //Bitmap bitmap_qr = ZXingUtils.createQRImage(content, iv_qr.getWidth(),  iv_qr.getHeight());
        //iv_qr.setImageBitmap(bitmap_qr);

        Bitmap bitmap_qr = ZXingUtils.createQRImage(content, 300,  300);
        times = (int)(System.currentTimeMillis() / 1000);
        iv_qr.setImageBitmap(bitmap_qr);

    }

    private final Handler handler = new Handler();

    private final Runnable task = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            if (run) {
                handler.postDelayed(this, 1000);
                count++;
                //times = (int)(System.currentTimeMillis() / 1000);
                getMSCardInfoRequest();
            }


            if(count==60){
                String content = "{\"userID\":"+ "\""+ LogStateInfo.getInstance(PayCodeActivity.this).getUserID() +"\""+ ",\"timeStamp\":"+ "\""+ times +"\"}";
                createBitMap(content);
                System.out.println(content);
                count = 0;
            }

            System.out.println(count);

        }


    };


    //public void onClick(View view){
    //    Button Gt_Choose_Point = (Button)findViewById(R.id.button_gt_choose_point);
    //    Intent intent = new Intent(PayCodeActivity.this,PayingActivity.class);
    //    startActivity(intent);
    //}

    //获得二维码返回结果
    private void getMSCardInfoRequest() {
        String url="http://193.112.44.141:80/citi/pay/QRCode";
        RequestQueue queue = MyApplication.getHttpQueues();
        //RequestQueue queue=Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("success",s);
                System.out.println(s);
                try {
                    JSONObject jObj = new JSONObject(s);

                    if(jObj.has("status")){
                        String status = jObj.getString("status");
                        System.out.println(status);
                    }
                    else {
                        //"originalPrice":1.0,"priceAfter":180.0,"pointsNeeded":200.0
                        run = false;
                        double originalPrice = jObj.getDouble("originalPrice");
                        double priceAfter = jObj.getDouble("priceAfter");
                        double pointsNeeded = jObj.getDouble("pointsNeeded");
                        String merchantName = jObj.getString("merchantName");
                        String logoURL = jObj.getString("merchantLogoURL");

                        Intent intentToFinish = new Intent(PayCodeActivity.this,PayCodeFinishActivity.class);
                        Bundle bundle = new Bundle();

                        bundle.putDouble("originalPrice",originalPrice);
                        bundle.putDouble("priceAfter",priceAfter);
                        bundle.putDouble("pointsNeeded",pointsNeeded);
                        bundle.putString("merchantName",merchantName);
                        bundle.putString("merchantLogoURL",logoURL);

                        intentToFinish.putExtras(bundle);
                        //
                        startActivity(intentToFinish);
                        finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("userID",LogStateInfo.getInstance(PayCodeActivity.this).getUserID());
                map.put("timeStamp", String.valueOf(times));
                System.out.println("请求userID:"+ LogStateInfo.getInstance(PayCodeActivity.this).getUserID()+"timeStamp"+String.valueOf(times));
                return map;
            }
        };
        queue.add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    @Override
    //重写onKeyDown方法,对按键(不一定是返回按键)监听
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            run = false;
            finish();
        }
        return false;
    }
}
