package com.citiexchangeplatform.pointsleague;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.citiexchangeplatform.pointsleague.base.CompatStatusBarActivity;
import com.citiexchangeplatform.pointsleague.util.SharedPreferencesUtil;

/**
 * 开屏页
 *
 */
public class SplashActivity extends Activity {


    private static final int sleepTime = 2000;
    Boolean isFirstOpen;

    @Override
    protected void onCreate(Bundle arg0) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);

        final View view = View.inflate(this, R.layout.activity_splash, null);
        setContentView(view);
        super.onCreate(arg0);
        isFirstOpen = SharedPreferencesUtil.getBoolean(this,SharedPreferencesUtil.FIRST_OPEN, true);


    }

    @Override
    protected void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            public void run() {
                long start = System.currentTimeMillis();
                long costTime = System.currentTimeMillis() - start;
                //等待sleeptime时长
                if (sleepTime - costTime > 0) {
                    try {
                        Thread.sleep(sleepTime - costTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 跳转
                Intent intent = new Intent();
                if (isFirstOpen) {
                    // 跳转引导页
                    intent.setClass(SplashActivity.this, GuideActivity.class);
                    // 从此不再是首次启动
                    SharedPreferencesUtil.putBoolean(SplashActivity.this, SharedPreferencesUtil.FIRST_OPEN, false);
                } else {
                    // 跳转主界面
                    intent.setClass(SplashActivity.this, MainActivity.class);
                }
                //进入主页面
                startActivity(intent);
                finish();
            }
        }).start();
    }
}
