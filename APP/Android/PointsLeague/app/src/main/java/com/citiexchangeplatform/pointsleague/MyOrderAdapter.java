package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.citiexchangeplatform.pointsleague.models.OrderItemModel;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyViewHolder> {
    //数据源
    private List<OrderItemModel> items;

    private Context context;
    //构造方法


    public MyOrderAdapter(Context context) {
        this.items = new ArrayList<OrderItemModel>();

        this.context = context;
    }

    //private String merchantName;
    //    private double originalPrice;
    //    private double priceAfter;
    //    private double pointsNeeded;
    //    private String date;
    public void addData(String name, double originalPrice,double priceAfter,double pointsNeeded,String logoURL,String date){
        OrderItemModel itemModel = new OrderItemModel(name,originalPrice,priceAfter,pointsNeeded,logoURL,date);
        items.add(itemModel);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*列表布局*/
        return new MyOrderAdapter.MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_my_order, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.MyViewHolder holder, int position) {
        //设置订单信息

        Double pointsNeed = items.get(position).getPointsNeeded();
        //保留两位小数
        NumberFormat nf = NumberFormat.getNumberInstance();
        // 保留两位小数
        nf.setMaximumFractionDigits(2);
        // 如果不需要四舍五入，可以使用RoundingMode.DOWN
        nf.setRoundingMode(RoundingMode.UP);
        String result = nf.format(pointsNeed);
        holder.pointsUsed.setText(result);

        Glide.with(context)
                .load(items.get(position).getLogoURL())
                .placeholder(R.drawable.ic_points_black_24dp)
                .error(R.drawable.ic_mall_black_24dp)
                .override(60,60)
                .into(holder.logo);

        holder.date.setText(items.get(position).getDate());

        //private String merchantName;
        //    private double originalPrice;
        //    private double priceAfter;
        //    private double pointsNeeded;
        //    private String logoURL;
        //    private String date;
        final String logoURL = items.get(position).getLogoURL();
        final String name = items.get(position).getMerchantName();
        final String time = items.get(position).getDate();
        final Double pointsNeeded = items.get(position).getPointsNeeded();
        final Double originalPrice = items.get(position).getOriginalPrice();
        final Double priceAfter = items.get(position).getPriceAfter();



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToDetails = new Intent(context,OrderDetailActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("logoURL",logoURL);
                bundle.putString("name",name);
                bundle.putString("time",time);
                bundle.putDouble("pointsNeeded",pointsNeeded);
                bundle.putDouble("originalPrice",originalPrice);
                bundle.putDouble("priceAfter",priceAfter);



                intentToDetails.putExtras(bundle);
                //
                context.startActivity(intentToDetails);


            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    /**
     * ViewHolder类，用于缓存控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        View view;
        //兑换描述
        TextView pointsUsed;
        //时间
        TextView date;

        //logo
        ImageView logo;


        public MyViewHolder(View view) {
            super(view);

            pointsUsed = view.findViewById(R.id.textView_use_point);
            date = view.findViewById(R.id.textView_order_date);
            logo = view.findViewById(R.id.image_order_logo);


        }


    }

}
