package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.citiexchangeplatform.pointsleague.models.ExchangeResultModel;

import java.util.List;

public class PayingFinishAdapter extends RecyclerView.Adapter<PayingFinishAdapter.MyViewHolder> {
    //数据源
    private List<ExchangeResultModel> exchangeResultModels;
    /*private List<String> points_used;
    private List<String> points_exchanged;
    private List<String> names;
    private List<String> logos;
    private List<String> reasons;*/
    private  Boolean state;
    private Context context;

    //构造方法

    public PayingFinishAdapter(Context context, Boolean state) {
        this.context = context;
        this.state = state;
    }

    /*public PayingFinishAdapter(Boolean state,List<String> bNames,List<String> logoURLs,List<String> used,List<String> exchanged, List<String> reasons, Context context) {
        this.state = state;
        this.names = bNames;
        this.points_used = used;
        this.points_exchanged = exchanged;
        this.reasons = reasons;
        this.logos = logoURLs;
        this.context = context;

    }*/

    public void addData(List<ExchangeResultModel> exchangeResultModels){
        this.exchangeResultModels = exchangeResultModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PayingFinishAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*列表布局*/
        return new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_paying_finish, parent, false));
    }


    /*为列表内容配置数据*/
    @Override
    public void onBindViewHolder(@NonNull final PayingFinishAdapter.MyViewHolder holder, final int position) {
        if(state){
            //使用的积分
            holder.Details.setText(String.valueOf(exchangeResultModels.get(position).getUsePoints()));
            //设置列表中积分信息

            //holder.pointsExchange.setText(points_exchanged.get(position));
        }
        else {
            holder.Details.setText(exchangeResultModels.get(position).getReason());
        }

        //设置商家图片
        //Glide.with(context)
        //        .load(logos.get(position))
        //        .placeholder(R.drawable.ic_points_black_24dp)
        //        .error(R.drawable.ic_mall_black_24dp)
        //        .override(60,60)
        //        .into(holder.logo);
        //holder.logo.setImageResource(img_list.get(position));
        //设置商户名
        holder.name.setText(exchangeResultModels.get(position).getMerchantName());


    }

    /*返回列表长度*/
    @Override
    public int getItemCount() {
        return exchangeResultModels.size();
    }


    /**
     * ViewHolder类，用于缓存控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView Details;
        //TextView falseReason;
        //ImageView logo;
        //TextView pointsExchange;
        TextView name;




        public MyViewHolder(View view) {
            super(view);
            Details = view.findViewById(R.id.textview_business_used_finish);
            //logo = view.findViewById(R.id.image_finish_business);
            name = view.findViewById(R.id.textview_business_name);



        }


    }
}
