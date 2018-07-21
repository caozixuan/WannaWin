package com.citiexchangeplatform.pointsleague.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.citiexchangeplatform.pointsleague.R;

public class EmptyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerView.Adapter mAdapter; //需要装饰的Adapter
    private Context mContext;
    private final int EMPTY_VIEW = 0;
    private final int NOT_EMPTY_VIEW = 1;

    public EmptyAdapter(RecyclerView.Adapter adapter, Context context) {
        mAdapter = adapter;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //展示空视图或者调用传入adapter方法
        if(viewType==EMPTY_VIEW){
            return new EmptyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_empty,parent,false));
        }
        return mAdapter.onCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof EmptyViewHolder){
            return;
        }
        mAdapter.onBindViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        //获取传入adapter的条目数，没有则返回 1
        if(mAdapter!=null){
            if(mAdapter.getItemCount()>0){
                return mAdapter.getItemCount();
            }
        }
        //位空视图保留一个条目
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        //根据传入adapter来判断是否有数据
        if(mAdapter!=null){
            if(mAdapter.getItemCount()>0){
                return NOT_EMPTY_VIEW;
            }
        }
        return EMPTY_VIEW;
    }

    public void notifyAdapter() {
        //有些时候数据的变化不仅要刷新当前adapter还要刷新传入的原始adapter
//        if(mAdapter!=null){
//            mAdapter.notifyDataSetChanged();
//        }
        notifyDataSetChanged();
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder{

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}