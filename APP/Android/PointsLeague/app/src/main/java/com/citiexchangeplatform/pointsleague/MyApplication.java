package com.citiexchangeplatform.pointsleague;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.study.xuan.xvolleyutil.base.XVolley;

public class MyApplication extends Application {

    public static RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        // 不必为每一次HTTP请求都创建一个RequestQueue对象，推荐在application中初始化
        requestQueue = Volley.newRequestQueue(this);
        XVolley.create(getApplicationContext());
    }

    public static RequestQueue getHttpQueues() {
        return requestQueue;
     }
}
