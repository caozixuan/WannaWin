package com.citiexchangeplatform.pointsleague.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.citiexchangeplatform.pointsleague.PayingFinishAdapter;
import com.citiexchangeplatform.pointsleague.R;
import com.citiexchangeplatform.pointsleague.models.ExchangeResultModel;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class PaymentFinishViewPagerAdapter extends PagerAdapter {
    Context context;
    private Boolean state;
    private List<ExchangeResultModel> exchangeResultModels;


    //水平分页的指示器
    //private List<View> indicators = new ArrayList<>();

    //水平分页的容器
    //private LinearLayout llIndicators;

    //public List<View> getIndicators() {
    //    return indicators;
    //}


    public PaymentFinishViewPagerAdapter(Context context, Boolean state) {
        this.context = context;
        this.state = state;
    }

    public void addData(List<ExchangeResultModel> exchangeResultModels){
        this.exchangeResultModels = exchangeResultModels;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int count = exchangeResultModels.size() % 3;
        int divide = exchangeResultModels.size() / 3;

        return count == 0 ? divide : divide + 1;
    }

    @NonNull
    @Override
    public RecyclerView instantiateItem(@NonNull ViewGroup container, int position) {
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        List<ExchangeResultModel> list = new ArrayList<ExchangeResultModel>();

        PayingFinishAdapter mAdapter = new PayingFinishAdapter(context,state);

        //每页最多显示3个，小于数据集总数，且小于下一页开始的位置索引
        for (int i = position * 3; i < (position + 1) * 3 && i < exchangeResultModels.size(); i++) {
            list.add(exchangeResultModels.get(i));
        }

        //初始化指示器。position == 0只初始化一次;且有多页；
        //for (int i = 0; position == 0 && getCount() != 1 && i < getCount(); i++) {
        //    View indicator = new View(context);
        //    Drawable drawable = context.getResources().getDrawable(R.drawable.indicator_selector);
        //
        //    indicator.setBackground(drawable);
        //    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(24, 24);
        //    params.setMarginEnd(10);
        //    llIndicators.addView(indicator, params);
        //    indicators.add(indicator);
        //}


        recyclerView.setAdapter(mAdapter);

        mAdapter.addData(list);

        container.addView(recyclerView);
        return recyclerView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeViewAt(position);
    }
}
