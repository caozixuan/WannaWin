package com.citiexchangeplatform.pointsleague;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_pay_code);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        iv_bar = (ImageView)findViewById(R.id.imageview_barcode);
        iv_qr = (ImageView)findViewById(R.id.imageview_qr);


        String content = "{\"userID\":"+ LogStateInfo.getInstance(PayCodeActivity.this).getUserID() +",\"timeStamp\":"+ times +"\"}";
        createBitMap(content);

        //handler.post(task);
        run = true;
        handler.postDelayed(task, 1000);


    }

    public void createBitMap(String content) {

        //清空原有图片
        iv_bar.setImageDrawable(null);
        iv_qr.setImageDrawable(null);
        /*生成二维码、条形码*/
        /*生成条形码*/

        //Bitmap bitmap_bar = ZXingUtils.creatBarcode(getApplicationContext(), content, iv_bar.getWidth(),  iv_bar.getHeight(),true);
        //iv_bar.setImageBitmap(bitmap_bar);

        Bitmap bitmap_bar = ZXingUtils.creatBarcode(getApplicationContext(), content,400,  200,false);
        iv_bar.setImageBitmap(bitmap_bar);
        /*生成二维码*/
        //Bitmap bitmap_qr = ZXingUtils.createQRImage(content, iv_qr.getWidth(),  iv_qr.getHeight());
        //iv_qr.setImageBitmap(bitmap_qr);

        Bitmap bitmap_qr = ZXingUtils.createQRImage(content, 300,  300);
        //times = (int)(System.currentTimeMillis() / 1000)-65;
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
                times = (int)(System.currentTimeMillis() / 1000);
            }
            getMSCardInfoRequest();
            if(count==60){
                String content = "{\"userID\":"+ "\""+ LogStateInfo.getInstance(PayCodeActivity.this).getUserID() +"\""+ ",\"timeStamp\":"+ "\""+ times +"\"}";
                createBitMap(content);
                System.out.println(content);
                count = 0;
            }

            System.out.println(count);

        }


    };


    public void onClick(View view){
        Button Gt_Choose_Point = (Button)findViewById(R.id.button_gt_choose_point);
        Intent intent = new Intent(PayCodeActivity.this,PayingActivity.class);
        startActivity(intent);
    }

    //获得二维码状态
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
                    String status = jObj.getString("status");
                    System.out.println(status);
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
                return map;
            }
        };
        queue.add(request);
    }

}
