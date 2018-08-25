package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.build.PostFormBuilder;
import com.study.xuan.xvolleyutil.callback.CallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class DetailFindPayActivity extends AppCompatActivity {

    ProgressDialog dialog;
    String itemID;
    double points;
    ImageView imageViewLogo;
    TextView textViewPoints;
    TextView textViewDescription;
    TextView textViewOverdue;
    TextView textViewNumber;
    TextView textViewTotal;
    int number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_find_pay);

        toolBar();

        Intent intent = getIntent();
        itemID = intent.getStringExtra("itemID");

        imageViewLogo = (ImageView) findViewById(R.id.imageView_detail_find_pay);
        textViewPoints = (TextView) findViewById(R.id.textView_points_detail_find_pay);
        textViewDescription = (TextView) findViewById(R.id.textView_description_detail_find_pay);
        textViewOverdue = (TextView) findViewById(R.id.textView_time_detail_find_pay);
        textViewNumber = (TextView) findViewById(R.id.textView_number_detail_find_pay);
        textViewTotal = (TextView) findViewById(R.id.textView_total_detail_find_pay);
        number = 1;

        Button buttonMinus = (Button) findViewById(R.id.button_minus_detail_find_pay);
        Button buttonAdd = (Button) findViewById(R.id.button_add_detail_find_pay);

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number == 1)
                    return;
                number--;
                textViewNumber.setText(String.valueOf(number));
                textViewTotal.setText(String.format("%.2fP", points * number));
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number++;
                textViewNumber.setText(String.valueOf(number));
                textViewTotal.setText(String.format("%.2fP", points * number));

            }
        });

        Button buttonBuy = findViewById(R.id.button_buy_detail_find_pay);
        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryPay(itemID);
            }
        });


        getInfo(itemID);

    }

    public void toolBar() {
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        TitleBar titleBar = (TitleBar) findViewById(R.id.toolbar_detail_find_pay);
        titleBar.setLeftImageResource(R.drawable.ic_left_orange_24dp);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextColor(0xFFFF9546);

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


    private void getInfo(final String itemID){
        String url="http://193.112.44.141:80/citi/item/itemDetail";
        dialog = ProgressDialog.show(DetailFindPayActivity.this, "", "正在加载优惠信息...");
        RequestQueue queue = MyApplication.getHttpQueues();

        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("success",s);
                System.out.println(s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String name = jsonObject.getString("name");
                    String description = jsonObject.getString("description");
                    String logoURL = jsonObject.getString("logoURL");
                    String overdueTime = jsonObject.getString("overdueTime");
                    points = jsonObject.getDouble("points");

                    Glide.with(DetailFindPayActivity.this)
                            .load(logoURL)
                            .centerCrop()
                            .error(R.drawable.loading_card)
                            .into(imageViewLogo);
                    textViewPoints.setText(String.format("%.2fP", points));
                    textViewTotal.setText(String.format("%.2fP", points));
                    textViewDescription.setText(description);
                    textViewOverdue.setText("有效期截至" + overdueTime);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                dialog.dismiss();
                Toast.makeText(DetailFindPayActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("itemID",itemID);
                if (LogStateInfo.getInstance(DetailFindPayActivity.this).isLogin()) {
                    map.put("userID", LogStateInfo.getInstance(DetailFindPayActivity.this).getUserID());
                }
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    private void getInfo2(String itemID){
    	
        PostFormBuilder url =
                XVolley.getInstance()
                        .doPost()
                        .url("http://193.112.44.141:80/citi/item/itemDetail")
                        .addParam("itemID", itemID);

        if (LogStateInfo.getInstance(DetailFindPayActivity.this).isLogin()) {
            url = url.addParam("userID", LogStateInfo.getInstance(DetailFindPayActivity.this).getUserID());
        }

        url.build().execute(DetailFindPayActivity.this, new CallBack<String>() {
            @Override
            public void onSuccess(Context context, String response) {
                System.out.println(response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String name = jsonObject.getString("name");
                    String description = jsonObject.getString("description");
                    String logoURL = jsonObject.getString("logoURL");
                    String overdueTime = jsonObject.getString("overdueTime");
                    points = jsonObject.getDouble("points");

                    Glide.with(DetailFindPayActivity.this)
                            .load(logoURL)
                            .centerCrop()
                            .error(R.drawable.loading_card)
                            .into(imageViewLogo);
                    textViewPoints.setText(String.format("%.2fP", points));
                    textViewTotal.setText(String.format("%.2fP", points));
                    textViewDescription.setText(description);
                    textViewOverdue.setText("有效期截至" + overdueTime);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();

            }

            @Override
            public void onError(VolleyError error) {
                super.onError(error);
                dialog.dismiss();
                Toast.makeText(DetailFindPayActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onBefore() {
                super.onBefore();
                dialog = ProgressDialog.show(DetailFindPayActivity.this, "", "正在加载优惠信息...");
            }
        });
    }

    private void tryPay(String itemID) {
        if (!LogStateInfo.getInstance(DetailFindPayActivity.this).isLogin()) {
            Toast.makeText(DetailFindPayActivity.this, "请登录后兑换", Toast.LENGTH_LONG).show();
            return;
        }
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/item/buyMultiple")
                .addParam("userID", LogStateInfo.getInstance(DetailFindPayActivity.this).getUserID())
                .addParam("itemID", itemID)
                .addParam("count", String.valueOf(number))
                .build()
                .execute(DetailFindPayActivity.this, new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);

                        boolean paySuccess = false;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            paySuccess = jsonObject.getBoolean("status");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();

                        if (paySuccess) {
                            Intent intentToCouponUnused = new Intent(DetailFindPayActivity.this, MyOrderActivity.class);
                            Toast.makeText(DetailFindPayActivity.this, "兑换成功", Toast.LENGTH_SHORT).show();
                            startActivity(intentToCouponUnused);
                            finish();
                        } else {
                            Toast.makeText(DetailFindPayActivity.this, "兑换失败", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        dialog.dismiss();
                        Toast.makeText(DetailFindPayActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBefore() {
                        super.onBefore();
                        dialog = ProgressDialog.show(DetailFindPayActivity.this, "", "正在支付...");
                    }
                });
    }
}
