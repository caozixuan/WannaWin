package com.citiexchangeplatform.pointsleague;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.citiexchangeplatform.pointsleague.base.CompatStatusBarActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class UserSettingActivity extends CompatStatusBarActivity {

    ListView listViewMenu;
    //ArrayList<String> menuItem = new ArrayList<String>(Arrays.asList("积分兑换记录","查看历史订单","绑定花旗账户","通用","反馈","关于"));
    ArrayList<String> menuItem = new ArrayList<String>(Arrays.asList("绑定花旗账户","修改密码"));
    ArrayList<Integer> menuIcon = new ArrayList<Integer>(Arrays.asList(R.drawable.binding_account,R.drawable.modify_password));



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        setStatusBarPlaceVisible(false);
        //setViewColorStatusBar(false, Color.WHITE);
        //状态栏文字图标暗色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        toolBar();

        listViewMenu = (ListView)findViewById(R.id.listView_menu_account);
        MenuListAdapter menuListAdapter = new MenuListAdapter(UserSettingActivity.this,menuItem,menuIcon);
        listViewMenu.setAdapter(menuListAdapter);

        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){

                    case 0:
                        Intent intentToBind = new Intent(UserSettingActivity.this, BindAccountActivity.class);
                        startActivity(intentToBind);
                        break;
                    case 1:
                        Intent intentToModifyPassword = new Intent(UserSettingActivity.this, ModifyPasswordActivity.class);
                        startActivity(intentToModifyPassword);

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
        titleBar.setTitle("用户设置");
        titleBar.setTitleSize(20);
        titleBar.setTitleColor(Color.BLACK);
        titleBar.setBackgroundColor(Color.TRANSPARENT);
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
