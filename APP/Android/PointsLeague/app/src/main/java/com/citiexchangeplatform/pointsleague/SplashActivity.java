package com.citiexchangeplatform.pointsleague;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.citiexchangeplatform.pointsleague.base.CompatStatusBarActivity;

/**
 * 开屏页
 *
 */
public class SplashActivity extends Activity {


    private static final int sleepTime = 2000;

    @Override
    protected void onCreate(Bundle arg0) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);

        final View view = View.inflate(this, R.layout.activity_splash, null);
        setContentView(view);
        super.onCreate(arg0);



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
                //进入主页面
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }).start();
    }
}
