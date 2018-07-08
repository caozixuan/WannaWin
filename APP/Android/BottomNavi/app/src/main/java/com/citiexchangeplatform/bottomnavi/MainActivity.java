package com.citiexchangeplatform.bottomnavi;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private Fragment []mFragmensts;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpaceNavigationView spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("积分", R.drawable.ic_home_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("发现", R.drawable.ic_notifications_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("商城", R.drawable.ic_home_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("我的", R.drawable.ic_notifications_black_24dp));

        spaceNavigationView.setCentreButtonIcon(R.drawable.ic_dashboard_black_24dp);
        spaceNavigationView.setCentreButtonColor(ContextCompat.getColor(this, R.color.colorPrimary));
        spaceNavigationView.setCentreButtonRippleColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }


}