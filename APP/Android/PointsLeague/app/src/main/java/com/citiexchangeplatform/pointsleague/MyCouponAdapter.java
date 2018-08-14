package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.citiexchangeplatform.pointsleague.models.CouponItemModel;

import java.util.ArrayList;
import java.util.List;

public class MyCouponAdapter extends RecyclerView.Adapter<MyCouponAdapter.MyViewHolder> {
    //数据源
    private List<CouponItemModel> items;
    private String type;

    private Context context;
    //构造方法


    public MyCouponAdapter(Context context) {
        this.items = new ArrayList<CouponItemModel>();

        this.context = context;
    }

    public void addData(String name, String description,String date,String validityTerm,String logoURL,String itemID,String type){
        CouponItemModel itemModel = new CouponItemModel(name,description,date,validityTerm,logoURL,itemID);
        items.add(itemModel);
        this.type = type;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyCouponAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*列表布局*/
        return new MyCouponAdapter.MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_my_coupon, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCouponAdapter.MyViewHolder holder, int position) {
        //设置订单信息
        holder.name.setText(items.get(position).getMerchantName());
        holder.description.setText(items.get(position).getDescription());
        holder.date.setText(items.get(position).getExchangeDate());

        final String logoURL = items.get(position).getLogoURL();
        final String name = items.get(position).getMerchantName();
        final String exchangeDate = items.get(position).getExchangeDate();
        final String validityDate = items.get(position).getValidityDate();
        final String itemID = items.get(position).getItemID();
        final String description = items.get(position).getDescription();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToDetails = new Intent(context,CouponsDetailsActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("logoURL",logoURL);
                bundle.putString("name",name);
                bundle.putString("exchangeDate",exchangeDate);
                bundle.putString("validityDate",validityDate);
                bundle.putString("itemID",itemID);
                bundle.putString("description",description);
                bundle.putString("type",type);

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
        TextView description;
        //时间
        TextView date;
        //商户名
        TextView name;


        public MyViewHolder(View view) {
            super(view);

            description = view.findViewById(R.id.textView_order_description);
            name = view.findViewById(R.id.textView_order_name);
            date = view.findViewById(R.id.textView_order_date);


        }


    }

}
