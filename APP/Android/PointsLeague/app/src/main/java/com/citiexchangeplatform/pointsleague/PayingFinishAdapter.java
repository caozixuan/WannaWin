package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PayingFinishAdapter extends RecyclerView.Adapter<PayingFinishAdapter.MyViewHolder> {
    //数据源
    private List<String> points_used;
    private List<String> points_exchanged;
    private List<String> names;
    private List<String> logos;
    private List<String> reasons;
    private  Boolean state;
    private Context context;

    //构造方法
    public PayingFinishAdapter(Boolean state,List<String> bNames,List<String> logoURLs,List<String> used,List<String> exchanged, List<String> reasons, Context context) {
        this.state = state;
        this.names = bNames;
        this.points_used = used;
        this.points_exchanged = exchanged;
        this.reasons = reasons;
        this.logos = logoURLs;
        this.context = context;

    }

    @NonNull
    @Override
    public PayingFinishAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*列表布局*/
        if(state){
            return new MyViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.item_paying_finish, parent, false));
        }
        else {
            return new MyViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.item_paying_finish_false, parent, false));
        }

    }


    /*为列表内容配置数据*/
    @Override
    public void onBindViewHolder(@NonNull final PayingFinishAdapter.MyViewHolder holder, final int position) {
        if(state){
            //使用的积分
            holder.pointsUsed.setText(points_used.get(position));
            //设置列表中积分信息
            holder.pointsExchange.setText(points_exchanged.get(position));
        }
        else {
            holder.falseReason.setText(reasons.get(position));
        }

        //设置商家图片
        Glide.with(context)
                .load(logos.get(position))
                .placeholder(R.drawable.ic_points_black_24dp)
                .error(R.drawable.ic_mall_black_24dp)
                .override(60,60)
                .into(holder.logo);
        //holder.logo.setImageResource(img_list.get(position));
        //设置商户名
        holder.name.setText(names.get(position));


    }

    /*返回列表长度*/
    @Override
    public int getItemCount() {
        return points_used.size();
    }


    /**
     * ViewHolder类，用于缓存控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView pointsUsed;
        TextView falseReason;
        ImageView logo;
        TextView pointsExchange;
        TextView name;




        public MyViewHolder(View view) {
            super(view);
            if(state){
                pointsUsed = view.findViewById(R.id.textview_business_used_finish);
                pointsExchange = view.findViewById(R.id.textview_points_exchanged);
            }
            else {
                falseReason = view.findViewById(R.id.textview_false_reason);
            }
            logo = view.findViewById(R.id.image_finish_business);
            name = view.findViewById(R.id.textview_business_name);



        }


    }
}
