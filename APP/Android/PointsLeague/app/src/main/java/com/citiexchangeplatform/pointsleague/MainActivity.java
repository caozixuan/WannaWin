package com.citiexchangeplatform.pointsleague;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;
    private String TAG = MainActivity.class.getSimpleName();
    private PointsFragment pointsFragment;
    private FindFragment findFragment;
    private MallFragment mallFragment;
    private AccountFragment accountFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        /** 导航基础设置 包括按钮选中效果 导航栏背景色等 */
        bottomNavigationBar
                .setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                /**
                 *  setMode() 内的参数有三种模式类型：
                 *  MODE_DEFAULT 自动模式：导航栏Item的个数<=3 用 MODE_FIXED 模式，否则用 MODE_SHIFTING 模式
                 *  MODE_FIXED 固定模式：未选中的Item显示文字，无切换动画效果。
                 *  MODE_SHIFTING 切换模式：未选中的Item不显示文字，选中的显示文字，有切换动画效果。
                 */

                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                /**
                 *  setBackgroundStyle() 内的参数有三种样式
                 *  BACKGROUND_STYLE_DEFAULT: 默认样式 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC
                 *                                    如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
                 *  BACKGROUND_STYLE_STATIC: 静态样式 点击无波纹效果
                 *  BACKGROUND_STYLE_RIPPLE: 波纹样式 点击有波纹效果
                 */

                .setActiveColor("#007AFF") //选中颜色
                //.setInActiveColor("#e9e6e6") //未选中颜色
                .setBarBackgroundColor(R.color.colorWhite);//导航栏背景色

        /** 添加导航按钮 */
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.home_unselected, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.search_unselected, "发现"))
                .addItem(new BottomNavigationItem(R.drawable.qrcode, "二维码").setInActiveColor("#FF8022").setActiveColor("#FF8022"))
                .addItem(new BottomNavigationItem(R.drawable.store_unselected, "商城"))
                .addItem(new BottomNavigationItem(R.drawable.my_unselected, "我的"))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise(); //initialise 一定要放在 所有设置的最后一项

        setDefaultFragment();//设置默认导航栏

    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        pointsFragment = new PointsFragment();
        transaction.replace(R.id.tb, pointsFragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = this.getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (pointsFragment == null) {
                    pointsFragment = new PointsFragment();
                }
                transaction.replace(R.id.tb, pointsFragment);
                break;
            case 1:
                if (findFragment == null) {
                    findFragment = new FindFragment();
                }
                transaction.replace(R.id.tb, findFragment);
                break;
            case 2:
                if(!LogStateInfo.getInstance(MainActivity.this).isLogin()){
                    Toast.makeText(MainActivity.this, "请登录后使用二维码", Toast.LENGTH_LONG).show();
                }
                Intent intentToPayCode = new Intent(MainActivity.this, PayCodeActivity.class);
                startActivity(intentToPayCode);
                break;
            case 3:
                if (mallFragment == null) {
                    mallFragment = new MallFragment();
                }
                transaction.replace(R.id.tb, mallFragment);
                break;
            case 4:
                if (accountFragment == null) {
                    accountFragment = new AccountFragment();
                }
                transaction.replace(R.id.tb, accountFragment);
                break;

            default:
                break;
        }

        transaction.commit();// 事务提交
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {
        if(position == 2){
            Intent intentToPayCode = new Intent(MainActivity.this, PayCodeActivity.class);
            startActivity(intentToPayCode);
        }
    }
}
