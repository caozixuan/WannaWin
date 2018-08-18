package com.citiexchangeplatform.pointsleague;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.citiexchangeplatform.pointsleague.base.CompatStatusBarActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class UserSettingActivity extends CompatStatusBarActivity {

    ListView listViewMenu;
    //ArrayList<String> menuItem = new ArrayList<String>(Arrays.asList("积分兑换记录","查看历史订单","绑定花旗账户","通用","反馈","关于"));
    ArrayList<String> menuItem = new ArrayList<String>(Arrays.asList("积分兑换记录","我的订单","设置"));
    ArrayList<Integer> menuIcon = new ArrayList<Integer>(Arrays.asList(R.drawable.icon_exchange,R.drawable.icon_my_order_history, R.drawable.icon_setting));



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

        listViewMenu = (ListView)findViewById(R.id.listView_menu_account);
        MenuListAdapter menuListAdapter = new MenuListAdapter(getApplicationContext(),menuItem,menuIcon);
        listViewMenu.setAdapter(menuListAdapter);

        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){

                    case 0:
                        Intent intentToBind = new Intent(getApplicationContext(), BindAccountActivity.class);
                        startActivity(intentToBind);
                        break;

                }
            }
        });
    }
}
