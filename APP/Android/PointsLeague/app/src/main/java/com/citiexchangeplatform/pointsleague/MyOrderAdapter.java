package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.citiexchangeplatform.pointsleague.models.MyOrderItemModel;

import java.util.ArrayList;
import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyViewHolder> {
    //数据源
    private List<MyOrderItemModel> items;
    private List<String> merchantNames;
    private List<String> descriptions;
    private List<String> dates;
    private Context context;
    //构造方法


    public MyOrderAdapter(Context context) {
        this.items = new ArrayList<MyOrderItemModel>();

        this.context = context;
    }

    public void addData(String name, String description,String date){
        MyOrderItemModel itemModel = new MyOrderItemModel(name,description,date);
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
        holder.name.setText(items.get(position).getMerchantName());
        holder.description.setText(items.get(position).getDescription());
        holder.date.setText(items.get(position).getDate());
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
