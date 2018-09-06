package com.citiexchangeplatform.pointsleague;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.citiexchangeplatform.pointsleague.base.CompatStatusBarActivity;

public class GuideActivity extends CompatStatusBarActivity {

    private ViewPager viewPager;

    private Fragment[] mFragmentArrays = new Fragment[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        //设置导航栏
        setStatusBarPlaceVisible(false);

        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.tab_viewpager);

        mFragmentArrays[0] = GuideFragment1.newInstance();
        mFragmentArrays[1] = GuideFragment2.newInstance();

        PagerAdapter pagerAdapter = new GuideActivity.MyViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }

    final class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentArrays[position];
        }


        @Override
        public int getCount() {
            return mFragmentArrays.length;
        }

    }
}
