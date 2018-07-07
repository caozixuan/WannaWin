package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PayingFinishAdapter extends RecyclerView.Adapter<PayingFinishAdapter.MyViewHolder> {
    //数据源
    private List<String> list;
    private Context context;

    //构造方法
    public PayingFinishAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @Override
    public PayingFinishAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*列表布局*/
        PayingFinishAdapter.MyViewHolder holder = new PayingFinishAdapter.MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_paying_finish, parent, false));
        return holder;
    }


    /*为列表内容配置数据*/
    @Override
    public void onBindViewHolder(final PayingFinishAdapter.MyViewHolder holder, final int position) {
        holder.Points_Used.setText(list.get(position));

    }

    /*返回列表长度*/
    @Override
    public int getItemCount() {
        return list.size();
    }


    /**
     * ViewHolder类，用于缓存控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView Points_Used;
        ImageView Business_Image;
        TextView Points_Remain;




        public MyViewHolder(View view) {
            super(view);
            Points_Used = (TextView) view.findViewById(R.id.textview_points_used);
            Points_Remain = (TextView) view.findViewById(R.id.textview_points_remain);
            Business_Image = (ImageView) view.findViewById(R.id.image_finish_business);



        }


    }
}
