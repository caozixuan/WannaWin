package com.citiexchangeplatform.pointsleague;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.citiexchangeplatform.pointsleague.base.CompatStatusBarActivity;
import com.citiexchangeplatform.pointsleague.util.DataCleanManager;

import java.util.ArrayList;
import java.util.Arrays;

public class SettingActivity extends CompatStatusBarActivity {

    ListView listViewMenu;
    //ArrayList<String> menuItem = new ArrayList<String>(Arrays.asList("积分兑换记录","查看历史订单","绑定花旗账户","通用","反馈","关于"));
    ArrayList<String> menuItem = new ArrayList<String>(Arrays.asList("字体大小","清除缓存","反馈","关于"));
    ArrayList<Integer> menuIcon = new ArrayList<Integer>(Arrays.asList(R.drawable.font_size,R.drawable.clear,R.drawable.feed_back,R.drawable.about_us));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //设置沉浸式状态栏
        setStatusBarPlaceVisible(true);
        setViewColorStatusBar(true, Color.WHITE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        toolBar();
        setListViewMenu();


    }

    public void setListViewMenu(){
        listViewMenu = (ListView)findViewById(R.id.listView_menu_setting);
        MenuListAdapter menuListAdapter = new MenuListAdapter(SettingActivity.this,menuItem,menuIcon);
        listViewMenu.setAdapter(menuListAdapter);

        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){

                    case 0:

                        break;
                    case 1:
                        String size = "0M";
                        try {
                            size = DataCleanManager.getCacheSize(getCacheDir());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String alter = "缓存共"+size+", 确认要清除吗？";
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SettingActivity.this);
                        alertDialog.setTitle(alter).setPositiveButton("确认", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                DataCleanManager.deleteCache(getApplicationContext());
                            }
                        }).setNegativeButton("取消", null).show();
                        break;
                    case 2:
                        Intent intentToFeedback = new Intent(SettingActivity.this, FeedbackActivity.class);
                        startActivity(intentToFeedback);
                        break;
                    case 3:
                        Intent intentToAbout = new Intent(SettingActivity.this, AboutUsActivity.class);
                        startActivity(intentToAbout);
                        break;

                }
            }
        });
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
        titleBar.setTitle("通用设置");
        titleBar.setTitleSize(20);
        titleBar.setTitleColor(Color.BLACK);
        titleBar.setBackgroundColor(Color.WHITE);
        //titleBar.setDividerColor(Color.GRAY);
        //左侧
        titleBar.setLeftImageResource(R.drawable.ic_left_orange_24dp);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextColor(getResources().getColor(R.color.colorLightOrange));
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





        //沉浸式
        //titleBar.setImmersive(isImmersive);
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
