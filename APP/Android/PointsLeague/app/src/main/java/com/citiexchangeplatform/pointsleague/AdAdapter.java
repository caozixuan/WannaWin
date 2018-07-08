package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class AdAdapter extends PagerAdapter {
    private List<ImageView> img_list;
    private List<String> list;

    //构造方法
    public AdAdapter(List<String> list, List<ImageView> img_list) {
        this.img_list = img_list;
        this.list = list;

    }
    @Override
    /**
     * 获得页面的总数
     */
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    /**
     * 获得相应位置上的view
     * container  view的容器，其实就是viewpager自身
     * position     相应的位置
     */
    public Object instantiateItem(ViewGroup container, int position) {
        destroyItem(container,position,img_list.get(position % img_list.size()));
        container.addView(img_list.get(position % img_list.size()));
        return img_list.get(position % img_list.size());
    }

    @Override
    /**
     * 判断 view和object的对应关系
     */
    public boolean isViewFromObject(View view, Object object) {
        if (view == object) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    /**
     * 销毁对应位置上的object
     */
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        object = null;
    }
}

